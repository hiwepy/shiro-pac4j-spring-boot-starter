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

import org.apache.shiro.spring.boot.pac4j.ext.Pac4jRelativeUrlResolver;
import org.apache.shiro.spring.boot.pac4j.utils.CasClientUtils;
import org.apache.shiro.spring.boot.utils.StringUtils;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.util.CommonUtils;
import org.pac4j.cas.client.CasClient;
import org.pac4j.cas.client.direct.DirectCasClient;
import org.pac4j.cas.client.direct.DirectCasProxyClient;
import org.pac4j.cas.client.rest.CasRestBasicAuthClient;
import org.pac4j.cas.client.rest.CasRestFormClient;
import org.pac4j.cas.config.CasConfiguration;
import org.pac4j.cas.logout.CasLogoutHandler;
import org.pac4j.cas.logout.DefaultCasLogoutHandler;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.http.UrlResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 
 * @className	： ShiroPac4jCasConfiguration
 * @description	： TODO(描述这个类的作用)
 * @author 		： <a href="https://github.com/vindell">vindell</a>
 * @date		： 2018年2月8日 下午10:47:54
 * @version 	V1.0
 * @see http://blog.csdn.net/hxpjava1/article/details/77934056
 */
@Configuration
@AutoConfigureBefore( name = {
	"org.apache.shiro.spring.boot.ShiroPac4jClientsConfiguration"
})
@ConditionalOnWebApplication
@ConditionalOnClass({ SingleSignOutHttpSessionListener.class, CasConfiguration.class})
@ConditionalOnProperty(prefix = ShiroPac4jCasProperties.PREFIX, value = "enabled", havingValue = "true")
@EnableConfigurationProperties({ ShiroPac4jCasProperties.class, ShiroPac4jProperties.class, ServerProperties.class })
public class ShiroPac4jCasConfiguration {

	@Autowired
	private ShiroPac4jCasProperties casProperties;
	@Autowired
	private ShiroPac4jProperties pac4jProperties;
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
		return new Pac4jRelativeUrlResolver(serverProperties.getServlet().getContextPath());
	}
	
	@Bean
    public CasConfiguration casConfiguration(CasLogoutHandler<WebContext> logoutHandler, UrlResolver urlResolver) {

		CasConfiguration configuration = new CasConfiguration(casProperties.getCasServerLoginUrl(), casProperties.getCasProtocol() );
		
		if(casProperties.isAcceptAnyProxy() && StringUtils.hasText(casProperties.getAllowedProxyChains())) {	
			configuration.setAcceptAnyProxy(casProperties.isAcceptAnyProxy());
			configuration.setAllowedProxyChains(CommonUtils.createProxyList(casProperties.getAllowedProxyChains()));
		}
		
		if(StringUtils.hasText(casProperties.getEncoding())) {	
			configuration.setEncoding(casProperties.getEncoding());
		}
		configuration.setGateway(casProperties.isGateway());
		configuration.setLoginUrl(casProperties.getCasServerLoginUrl());
		configuration.setLogoutHandler(logoutHandler);
		if(StringUtils.hasText(casProperties.getServiceParameterName())) {	
			configuration.setPostLogoutUrlParameter(casProperties.getServiceParameterName());
		}
		configuration.setPrefixUrl(casProperties.getCasServerUrlPrefix());
		configuration.setProtocol(casProperties.getCasProtocol());
		//configuration.setRenew(pac4jProperties.isRenew());
		configuration.setRestUrl(casProperties.getCasServerRestUrl());
		configuration.setTimeTolerance(casProperties.getTolerance());
		configuration.setUrlResolver(urlResolver);
		
		return configuration;
	}

	@Bean
	@ConditionalOnProperty(prefix = ShiroPac4jCasProperties.PREFIX, value = "cas-client", havingValue = "true")
	public CasClient casClient(CasConfiguration configuration) {
		return CasClientUtils.casClient(configuration, pac4jProperties, casProperties, serverProperties);
	}
	
	@Bean
	@ConditionalOnProperty(prefix = ShiroPac4jCasProperties.PREFIX, value = "direct-cas-client", havingValue = "true")
	public DirectCasClient directCasClient(CasConfiguration configuration) {
		return CasClientUtils.directCasClient(configuration, casProperties);
	}
	
	@Bean
	@ConditionalOnProperty(prefix = ShiroPac4jCasProperties.PREFIX, value = "direct-cas-proxy-client", havingValue = "true")
	public DirectCasProxyClient directCasProxyClient(CasConfiguration configuration) {
		return CasClientUtils.directCasProxyClient(configuration, casProperties, casProperties.getCasServerUrlPrefix());
	}
	
	@Bean
	@ConditionalOnProperty(prefix = ShiroPac4jCasProperties.PREFIX, value = "cas-rest-basic-auth-client", havingValue = "true")
	public CasRestBasicAuthClient casRestBasicAuthClient(CasConfiguration configuration) {
		return CasClientUtils.casRestBasicAuthClient(configuration, casProperties);
	}
	
	@Bean
	@ConditionalOnProperty(prefix = ShiroPac4jCasProperties.PREFIX, value = "cas-rest-form-client", havingValue = "true")
	public CasRestFormClient casRestFormClient(CasConfiguration configuration) {
		return CasClientUtils.casRestFormClient(configuration, casProperties);
	}
    
}
