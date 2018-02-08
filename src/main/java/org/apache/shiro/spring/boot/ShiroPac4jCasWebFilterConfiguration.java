/*
 * Copyright (c) 2010-2020, vindell (https://github.com/vindell).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.apache.shiro.spring.boot;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.shiro.spring.boot.pac4j.ShiroPac4jFilterFactoryBean;
import org.apache.shiro.spring.boot.pac4j.ext.Pac4jRelativeUrlResolver;
import org.apache.shiro.spring.boot.pac4j.ext.filter.Pac4jUserFilter;
import org.apache.shiro.spring.boot.pac4j.utils.CasClientUtils;
import org.apache.shiro.spring.boot.pac4j.utils.CasUrlUtils;
import org.apache.shiro.spring.boot.utils.StringUtils;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.AbstractShiroWebFilterConfiguration;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.util.CommonUtils;
import org.pac4j.cas.client.CasClient;
import org.pac4j.cas.config.CasConfiguration;
import org.pac4j.cas.logout.CasLogoutHandler;
import org.pac4j.cas.logout.DefaultCasLogoutHandler;
import org.pac4j.core.authorization.authorizer.CheckHttpMethodAuthorizer;
import org.pac4j.core.client.Client;
import org.pac4j.core.client.Clients;
import org.pac4j.core.config.Config;
import org.pac4j.core.context.HttpConstants.HTTP_METHOD;
import org.pac4j.core.context.J2EContext;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.context.session.SessionStore;
import org.pac4j.core.http.AjaxRequestResolver;
import org.pac4j.core.http.DefaultAjaxRequestResolver;
import org.pac4j.core.http.HttpActionAdapter;
import org.pac4j.core.http.J2ENopHttpActionAdapter;
import org.pac4j.core.http.UrlResolver;
import org.pac4j.http.authorization.authorizer.IpRegexpAuthorizer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import io.buji.pac4j.context.ShiroSessionStore;
import io.buji.pac4j.filter.CallbackFilter;
import io.buji.pac4j.filter.LogoutFilter;
import io.buji.pac4j.filter.SecurityFilter;


/**
 * 
 * @className	： ShiroCasPac4jWebFilterConfiguration
 * @description	： TODO(描述这个类的作用)
 * @author 		： <a href="https://github.com/vindell">vindell</a>
 * @date		： 2018年1月25日 下午9:06:14
 * @version 	V1.0
 * @see http://blog.csdn.net/hxpjava1/article/details/77934056
 */
@Configuration
@AutoConfigureBefore( name = {
	"org.apache.shiro.spring.config.web.autoconfigure.ShiroWebFilterConfiguration",  // shiro-spring-boot-web-starter
	"org.apache.shiro.spring.boot.ShiroBizWebFilterConfiguration" // spring-boot-starter-shiro-biz
})
@ConditionalOnWebApplication
@ConditionalOnClass({CallbackFilter.class, SecurityFilter.class, LogoutFilter.class, CasConfiguration.class})
@ConditionalOnProperty(prefix = ShiroPac4jCasProperties.PREFIX, value = "enabled", havingValue = "true")
@EnableConfigurationProperties({ ShiroPac4jCasProperties.class, ShiroBizProperties.class, ServerProperties.class })
public class ShiroPac4jCasWebFilterConfiguration extends AbstractShiroWebFilterConfiguration implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Autowired
	private ShiroPac4jCasProperties pac4jProperties;
	@Autowired
	private ShiroBizProperties bizProperties;
	@Autowired
	private ServerProperties serverProperties;
	
	/**
	 * 单点登录Session监听器
	 */
	@Bean(name = "singleSignOutHttpSessionListener")
	public ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> singleSignOutHttpSessionListener() {
		ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> registration = new ServletListenerRegistrationBean<SingleSignOutHttpSessionListener>(
				new SingleSignOutHttpSessionListener());
		registration.setOrder(1);
		return registration;
	}
	
	@Bean
	@ConditionalOnMissingBean
    public CasLogoutHandler<WebContext> logoutHandler(){
		return new DefaultCasLogoutHandler<WebContext>();
	}
	
	@Bean
	@ConditionalOnMissingBean
	protected UrlResolver urlResolver() {
		return new Pac4jRelativeUrlResolver(serverProperties.getContextPath());
	}
	
	@Bean
    public CasConfiguration casConfiguration(CasLogoutHandler<WebContext> logoutHandler, UrlResolver urlResolver) {

		// 完整的cas登录地址,比如client项目的https://passport.xxx.com/login?service=https://client.xxx.com
		String serverLoginUrl = CasUrlUtils.constructLoginRedirectUrl(pac4jProperties, serverProperties.getContextPath(), pac4jProperties.getServerCallbackUrl());
		
		CasConfiguration configuration = new CasConfiguration(serverLoginUrl, pac4jProperties.getCasProtocol() );
		
		if(pac4jProperties.isAcceptAnyProxy() && StringUtils.hasText(pac4jProperties.getAllowedProxyChains())) {	
			configuration.setAcceptAnyProxy(pac4jProperties.isAcceptAnyProxy());
			configuration.setAllowedProxyChains(CommonUtils.createProxyList(pac4jProperties.getAllowedProxyChains()));
		}
		
		if(StringUtils.hasText(pac4jProperties.getEncoding())) {	
			configuration.setEncoding(pac4jProperties.getEncoding());
		}
		configuration.setGateway(pac4jProperties.isGateway());
		configuration.setLoginUrl(pac4jProperties.getCasServerLoginUrl());
		configuration.setLogoutHandler(logoutHandler);
		if(StringUtils.hasText(pac4jProperties.getServiceParameterName())) {	
			configuration.setPostLogoutUrlParameter(pac4jProperties.getServiceParameterName());
		}
		configuration.setPrefixUrl(pac4jProperties.getCasServerUrlPrefix());
		configuration.setProtocol(pac4jProperties.getCasProtocol());
		//configuration.setRenew(pac4jProperties.isRenew());
		configuration.setRestUrl(pac4jProperties.getCasServerRestUrl());
		configuration.setTimeTolerance(pac4jProperties.getTolerance());
		configuration.setUrlResolver(urlResolver);
		
		return configuration;
	}

	@Bean
	@ConditionalOnMissingBean
	protected AjaxRequestResolver ajaxRequestResolver() {
		return new DefaultAjaxRequestResolver();
	}
	
	@Bean
	@ConditionalOnMissingBean
	protected SessionStore<J2EContext> sessionStore() {
		return new ShiroSessionStore();
	}
	
	@Bean
	@ConditionalOnMissingBean
	protected HttpActionAdapter<Object, J2EContext> httpActionAdapter() {
		return J2ENopHttpActionAdapter.INSTANCE;
	}
	
	@SuppressWarnings("rawtypes")
	@Bean
	public Config casConfig(CasConfiguration configuration, AjaxRequestResolver ajaxRequestResolver, UrlResolver urlResolver,
			HttpActionAdapter<Object, J2EContext> httpActionAdapter,SessionStore<J2EContext> sessionStore) {

		final Clients clients = new Clients();
		final List<Client> clientList = new ArrayList<Client>();
		CasClient casClient = CasClientUtils.casClient(configuration, pac4jProperties, serverProperties);
		clientList.add(casClient);
		if(pac4jProperties.isDirectCasClient()) {
			clientList.add(CasClientUtils.directCasClient(configuration, pac4jProperties));
		}
		if(pac4jProperties.isDirectCasProxyClient()) {
			clientList.add(CasClientUtils.directCasProxyClient(configuration, pac4jProperties, pac4jProperties.getCasServerUrlPrefix()));
		}
		if(pac4jProperties.isCasRestBasicAuthClient()) {
			clientList.add(CasClientUtils.casRestBasicAuthClient(configuration, pac4jProperties));
		}
		if(pac4jProperties.isCasRestFormClient()) {
			clientList.add(CasClientUtils.casRestFormClient(configuration, pac4jProperties));
		}
		
		clients.setAjaxRequestResolver(ajaxRequestResolver);
		clients.setCallbackUrl(pac4jProperties.getServerCallbackUrl());
		clients.setClients(clientList);
		clients.setClientNameParameter(pac4jProperties.getClientParameterName());
		clients.setDefaultClient(casClient);
		clients.setUrlResolver(urlResolver);
		
		final Config config = new Config(clients);
		
		if(StringUtils.hasText(pac4jProperties.getAllowedIpRegexpPattern())) {	
			config.addAuthorizer("isIPAuthenticated", new IpRegexpAuthorizer(pac4jProperties.getAllowedIpRegexpPattern()));
		}
		if(ArrayUtils.isNotEmpty(pac4jProperties.getAllowedHttpMethods())) {	
			String[] allowedHttpMethods = pac4jProperties.getAllowedHttpMethods();
			List<HTTP_METHOD> methods = new ArrayList<HTTP_METHOD>();
			for (String method : allowedHttpMethods) {
				methods.add(HTTP_METHOD.valueOf(method));
			}
			config.addAuthorizer("isMethodAuthenticated", new CheckHttpMethodAuthorizer(methods));
		}
		
		/*excludePath
		excludeRegex
		excludeBranch
		
		[] methods
		private String headerName;
	    private String expectedValue;*/
	    
	    
		//config.addMatcher("path", new AntPathMatcher().excludePath("").excludeBranch("").excludeRegex(""));
		//config.addMatcher("header", new HeaderMatcher());
		//config.addMatcher("method", new HttpMethodMatcher());
		
		config.setClients(clients);
		config.setHttpActionAdapter(httpActionAdapter);
		config.setSessionStore(sessionStore);
		
		return config;
	}
 
	/**
	 * 账号注销过滤器 ：处理账号注销
	 */
	@Bean("logout")
	public FilterRegistrationBean logoutFilter(Config config){
		
		FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
		
		LogoutFilter logoutFilter = new LogoutFilter();
	    
		// Whether the centralLogout must be performed（是否注销统一身份认证）
        logoutFilter.setCentralLogout(pac4jProperties.isCentralLogout());
		// Security Configuration
        logoutFilter.setConfig(config);
        
        // Default logourl url
        logoutFilter.setDefaultUrl( CasUrlUtils.constructLogoutRedirectUrl(pac4jProperties, serverProperties.getContextPath(), bizProperties.getLoginUrl()) );
        // Whether the application logout must be performed（是否注销本地应用身份认证）
        logoutFilter.setLocalLogout(pac4jProperties.isLocalLogout());
        // Pattern that logout urls must match（注销登录路径规则，用于匹配登录请求操作）
        logoutFilter.setLogoutUrlPattern(pac4jProperties.getLogoutUrlPattern());
        
        filterRegistration.setFilter(logoutFilter);
	    filterRegistration.setEnabled(false); 
	    
	    return filterRegistration;
	}
	
	/**
	 * 权限控制过滤器 ：实现权限认证
	 */
	@Bean("authc")
	public FilterRegistrationBean casSecurityFilter(Config config){
		
		FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
		
		SecurityFilter securityFilter = new SecurityFilter();  
		
		// List of authorizers
		securityFilter.setAuthorizers(pac4jProperties.getAuthorizers());
		// List of clients for authentication
		securityFilter.setClients(pac4jProperties.getClients());
		// Security configuration
		securityFilter.setConfig(config);
		securityFilter.setMatchers(pac4jProperties.getMatchers());
		// Whether multiple profiles should be kept
		securityFilter.setMultiProfile(pac4jProperties.isMultiProfile());
		
        filterRegistration.setFilter(securityFilter);
	    filterRegistration.setEnabled(false); 
	    
	    return filterRegistration;
	}
	
	
	@Bean("user")
	public FilterRegistrationBean casSsoFilter(){
		FilterRegistrationBean registration = new FilterRegistrationBean(); 
		Pac4jUserFilter userFilter = new Pac4jUserFilter();
		userFilter.setLoginUrl(CasUrlUtils.constructLoginRedirectUrl(pac4jProperties, serverProperties.getContextPath(), pac4jProperties.getServerCallbackUrl()));
		registration.setFilter(userFilter);
	    registration.setEnabled(false); 
	    return registration;
	}
	
	
	/**
	 * 回调过滤器 ：处理登录后的回调访问
	 */
	@Bean("cas")
	public FilterRegistrationBean callbackFilter(Config config){
		
		FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
		
	    CallbackFilter callbackFilter = new CallbackFilter();
	    
	    // Security Configuration
        callbackFilter.setConfig(config);
        // Default url after login if none was requested（登录成功后的重定向地址，等同于shiro的successUrl）
        callbackFilter.setDefaultUrl(CasUrlUtils.constructCallbackUrl( serverProperties.getContextPath(), bizProperties.getSuccessUrl()));
        // Whether multiple profiles should be kept
        callbackFilter.setMultiProfile(pac4jProperties.isMultiProfile());
        
        filterRegistration.setFilter(callbackFilter);
	    filterRegistration.setEnabled(false); 
	    
	    return filterRegistration;
	}
	
	/**
	 * 权限控制过滤器 ：权限过滤链的入口（仅是FactoryBean需要引用）
	 */
	@Bean
    @Override
	protected ShiroFilterFactoryBean shiroFilterFactoryBean() {

		ShiroFilterFactoryBean filterFactoryBean = new ShiroPac4jFilterFactoryBean();
		
		// 登录地址：会话不存在时访问的地址
		filterFactoryBean.setLoginUrl(CasUrlUtils.constructLoginRedirectUrl(pac4jProperties, serverProperties.getContextPath(), CasUrlUtils.constructCallbackUrl(pac4jProperties)));
		// 系统主页：登录成功后跳转路径
		filterFactoryBean.setSuccessUrl(bizProperties.getSuccessUrl());
		// 异常页面：无权限时的跳转路径
		filterFactoryBean.setUnauthorizedUrl(bizProperties.getUnauthorizedUrl());
		
		// 必须设置 SecurityManager
		filterFactoryBean.setSecurityManager(securityManager);
		// 拦截规则
		filterFactoryBean.setFilterChainDefinitionMap(shiroFilterChainDefinition.getFilterChainMap());
		
		return filterFactoryBean;
	}
	
	/**
	 * 权限控制过滤器 ：权限过滤链的入口
	 */
    @Bean(name = "filterShiroFilterRegistrationBean")
    protected FilterRegistrationBean filterShiroFilterRegistrationBean() throws Exception {

        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter((AbstractShiroFilter) shiroFilterFactoryBean().getObject());
        filterRegistrationBean.setOrder(Ordered.LOWEST_PRECEDENCE);

        return filterRegistrationBean;
    }
    
    @Override
  	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
  		this.applicationContext = applicationContext;
  	}

  	public ApplicationContext getApplicationContext() {
  		return applicationContext;
  	}
    
}
