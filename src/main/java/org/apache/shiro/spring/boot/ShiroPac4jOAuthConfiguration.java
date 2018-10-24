/*
 * Copyright (c) 2017, vindell (https://github.com/vindell).
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

import org.apache.shiro.spring.boot.pac4j.ext.Pac4jRelativeUrlResolver;
import org.apache.shiro.spring.boot.pac4j.property.ShiroPac4jOAuthCasClientProperties;
import org.apache.shiro.spring.boot.pac4j.property.ShiroPac4jOAuthClientProperties;
import org.apache.shiro.spring.boot.pac4j.property.ShiroPac4jOAuthFacebookClientProperties;
import org.apache.shiro.spring.boot.pac4j.property.ShiroPac4jOAuthGenericProperties;
import org.apache.shiro.spring.boot.pac4j.property.ShiroPac4jOAuthOkClientProperties;
import org.apache.shiro.spring.boot.pac4j.property.ShiroPac4jOAuthStravaClientProperties;
import org.apache.shiro.util.CollectionUtils;
import org.pac4j.core.client.Client;
import org.pac4j.core.http.ajax.AjaxRequestResolver;
import org.pac4j.core.http.ajax.DefaultAjaxRequestResolver;
import org.pac4j.core.http.url.UrlResolver;
import org.pac4j.core.state.StateGenerator;
import org.pac4j.core.state.StaticOrRandomStateGenerator;
import org.pac4j.http.client.indirect.FormClient;
import org.pac4j.http.client.indirect.IndirectBasicAuthClient;
import org.pac4j.oauth.client.BaiduClient;
import org.pac4j.oauth.client.BitbucketClient;
import org.pac4j.oauth.client.CasOAuthWrapperClient;
import org.pac4j.oauth.client.DropBoxClient;
import org.pac4j.oauth.client.FacebookClient;
import org.pac4j.oauth.client.FoursquareClient;
import org.pac4j.oauth.client.GenericOAuth20Client;
import org.pac4j.oauth.client.GitHubClient;
import org.pac4j.oauth.client.Google2Client;
import org.pac4j.oauth.client.LinkedIn2Client;
import org.pac4j.oauth.client.OAuth10Client;
import org.pac4j.oauth.client.OAuth20Client;
import org.pac4j.oauth.client.OkClient;
import org.pac4j.oauth.client.OrcidClient;
import org.pac4j.oauth.client.PayPalClient;
import org.pac4j.oauth.client.SinaWeiboClient;
import org.pac4j.oauth.client.StravaClient;
import org.pac4j.oauth.client.TwitterClient;
import org.pac4j.oauth.client.VkClient;
import org.pac4j.oauth.client.WeiXinClient;
import org.pac4j.oauth.client.WindowsLiveClient;
import org.pac4j.oauth.client.WordPressClient;
import org.pac4j.oauth.client.YahooClient;
import org.pac4j.oauth.config.OAuth10Configuration;
import org.pac4j.oauth.config.OAuth20Configuration;
import org.pac4j.oauth.profile.OAuth10Profile;
import org.pac4j.oauth.profile.OAuth20Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureBefore( name = {
	"org.apache.shiro.spring.boot.ShiroPac4jClientsConfiguration"
})
@ConditionalOnWebApplication
@ConditionalOnClass({ FormClient.class, IndirectBasicAuthClient.class})
@ConditionalOnProperty(prefix = ShiroPac4jOAuthProperties.PREFIX, value = "enabled", havingValue = "true")
@EnableConfigurationProperties({ ShiroPac4jOAuthProperties.class, ShiroBizProperties.class, ServerProperties.class })
public class ShiroPac4jOAuthConfiguration {

	@Autowired
	private ShiroPac4jOAuthProperties oauthProperties; 
	@Autowired
	private ShiroPac4jProperties pac4jProperties;
	@Autowired
	private ServerProperties serverProperties;
	
	@Bean
	@ConditionalOnMissingBean
	protected AjaxRequestResolver ajaxRequestResolver() {
		return new DefaultAjaxRequestResolver();
	}
	
	@Bean
	@ConditionalOnMissingBean
	protected UrlResolver urlResolver() {
		return new Pac4jRelativeUrlResolver(serverProperties.getServlet().getContextPath());
	}
	
	@Bean
	@ConditionalOnMissingBean
	protected StateGenerator stateGenerator(ShiroPac4jOAuthGenericProperties properties) {
		StateGenerator stateGenerator = new StaticOrRandomStateGenerator();
		return stateGenerator;
	}
	
	/**
	 * 所有的标准 OAuth 2.0 协议的对接
	 */
	@Bean("oauth20Clients")
	@SuppressWarnings("rawtypes")
	@ConditionalOnProperty(prefix = ShiroPac4jOAuthProperties.PREFIX, value = "generics")
	public List<Client> oauth20Clients(AjaxRequestResolver ajaxRequestResolver, UrlResolver urlResolver, StateGenerator stateGenerator) {
		
		List<Client> oauth20Clients = new ArrayList<Client>();
		List<ShiroPac4jOAuthGenericProperties> generics = oauthProperties.getGenerics();
		if(!CollectionUtils.isEmpty(generics)) {
			
			for (ShiroPac4jOAuthGenericProperties properties : generics) {
				
				final GenericOAuth20Client client = new GenericOAuth20Client();
				
				final OAuth20Configuration configuration = client.getConfiguration();
				
				//configuration.setConnectTimeout(properties.getConnectTimeout());
				configuration.setCustomParams(properties.getCustomParams());
				//configuration.setHasGrantType(properties.isHasGrantType());
				//configuration.setReadTimeout(properties.getReadTimeout());
				configuration.setResponseType(properties.getResponseType());
				configuration.setScope(properties.getScope());
				configuration.setStateGenerator(stateGenerator);
				configuration.setTokenAsHeader(properties.isTokenAsHeader());
				configuration.setWithState(properties.isWithState());
				
				client.setName(properties.getName());
				client.setAjaxRequestResolver(ajaxRequestResolver);
				client.setAuthUrl(properties.getAuthUrl());
				client.setCallbackUrl(pac4jProperties.getCallbackUrl());
				client.setConfiguration(configuration);
				client.setCustomParams(properties.getCustomParams());
				// client.setIncludeClientNameInCallbackUrl(pac4jProperties.isIncludeClientNameInCallbackUrl());
				client.setProfileAttrs(properties.getProfileAttrs());
				client.setSecret(properties.getSecret());
				client.setTokenUrl(properties.getTokenUrl());
				client.setUrlResolver(urlResolver);
				
				oauth20Clients.add(client);
			}
			
		}
		
		return oauth20Clients;
		
	}
	
	@Bean
	@ConditionalOnProperty(prefix = ShiroPac4jOAuthProperties.PREFIX, value = "baidu")
	public BaiduClient baiduClient(AjaxRequestResolver ajaxRequestResolver, UrlResolver urlResolver) {
		
		final ShiroPac4jOAuthClientProperties properties = oauthProperties.getBaidu();
		final BaiduClient client = new BaiduClient(properties.getKey(), properties.getSecret());
		this.initOAuth20Client(client, properties, ajaxRequestResolver, urlResolver);
		
		return client;
	}
	
	@Bean
	@ConditionalOnProperty(prefix = ShiroPac4jOAuthProperties.PREFIX, value = "bitbucket")
	public BitbucketClient bitbucketClient(AjaxRequestResolver ajaxRequestResolver, UrlResolver urlResolver) {
		
		final ShiroPac4jOAuthClientProperties properties = oauthProperties.getBitbucket();
		final BitbucketClient client = new BitbucketClient(properties.getKey(), properties.getSecret());
		this.initOAuth10Client(client, properties, ajaxRequestResolver, urlResolver);
		
		return client;
	}
	
	@Bean
	@ConditionalOnProperty(prefix = ShiroPac4jOAuthProperties.PREFIX, value = "cas")
	public CasOAuthWrapperClient casOAuthWrapperClient(AjaxRequestResolver ajaxRequestResolver, UrlResolver urlResolver) {
		
		final ShiroPac4jOAuthCasClientProperties properties = oauthProperties.getCas();
		final CasOAuthWrapperClient client = new CasOAuthWrapperClient(properties.getKey(), properties.getSecret(), properties.getCasOAuthUrl());
		this.initOAuth20Client(client, properties, ajaxRequestResolver, urlResolver);
		
		return client;
	}
	
	@Bean
	@ConditionalOnProperty(prefix = ShiroPac4jOAuthProperties.PREFIX, value = "dropbox")
	public DropBoxClient dropboxClient(AjaxRequestResolver ajaxRequestResolver, UrlResolver urlResolver) {
		
		final ShiroPac4jOAuthClientProperties properties = oauthProperties.getDropbox();
		final DropBoxClient client = new DropBoxClient(properties.getKey(), properties.getSecret());
		this.initOAuth20Client(client, properties, ajaxRequestResolver, urlResolver);
		
		return client;
	}

	@Bean
	@ConditionalOnProperty(prefix = ShiroPac4jOAuthProperties.PREFIX, value = "facebook")
	public FacebookClient facebookClient(AjaxRequestResolver ajaxRequestResolver, UrlResolver urlResolver) {

		final ShiroPac4jOAuthFacebookClientProperties properties = oauthProperties.getFacebook();
		final FacebookClient client = new FacebookClient(properties.getKey(), properties.getSecret());
		this.initOAuth20Client(client, properties, ajaxRequestResolver, urlResolver);
		
		client.setFields(properties.getFields());
		//client.setRequiresExtendedToken(properties.isRequiresExtendedToken());
		client.setLimit(properties.getLimit());
		
		return client;
	}
	
	@Bean
	@ConditionalOnProperty(prefix = ShiroPac4jOAuthProperties.PREFIX, value = "foursquare")
	public FoursquareClient foursquareClient(AjaxRequestResolver ajaxRequestResolver, UrlResolver urlResolver) {
		
		final ShiroPac4jOAuthClientProperties properties = oauthProperties.getFoursquare();
		final FoursquareClient client = new FoursquareClient(properties.getKey(), properties.getSecret());
		this.initOAuth20Client(client, properties, ajaxRequestResolver, urlResolver);
		
		return client;
	}
	
	@Bean
	@ConditionalOnProperty(prefix = ShiroPac4jOAuthProperties.PREFIX, value = "github")
	public GitHubClient githubClient(AjaxRequestResolver ajaxRequestResolver, UrlResolver urlResolver) {
		
		final ShiroPac4jOAuthClientProperties properties = oauthProperties.getGithub();
		final GitHubClient client = new GitHubClient(properties.getKey(), properties.getSecret());
		this.initOAuth20Client(client, properties, ajaxRequestResolver, urlResolver);
		
		return client;
	}
	
	@Bean
	@ConditionalOnProperty(prefix = ShiroPac4jOAuthProperties.PREFIX, value = "google2")
	public Google2Client google2Client(AjaxRequestResolver ajaxRequestResolver, UrlResolver urlResolver) {
		
		final ShiroPac4jOAuthClientProperties properties = oauthProperties.getGoogle2();
		final Google2Client client = new Google2Client(properties.getKey(), properties.getSecret());
		this.initOAuth20Client(client, properties, ajaxRequestResolver, urlResolver);
		
		return client;
	}
	
	@Bean
	@ConditionalOnProperty(prefix = ShiroPac4jOAuthProperties.PREFIX, value = "linkedin2")
	public LinkedIn2Client linkedin2Client(AjaxRequestResolver ajaxRequestResolver, UrlResolver urlResolver) {
		
		final ShiroPac4jOAuthClientProperties properties = oauthProperties.getLinkedin2();
		final LinkedIn2Client client = new LinkedIn2Client(properties.getKey(), properties.getSecret());
		this.initOAuth20Client(client, properties, ajaxRequestResolver, urlResolver);
		
		return client;
	}
	
	@Bean
	@ConditionalOnProperty(prefix = ShiroPac4jOAuthProperties.PREFIX, value = "ok")
	public OkClient okClient(AjaxRequestResolver ajaxRequestResolver, UrlResolver urlResolver) {
		
		final ShiroPac4jOAuthOkClientProperties properties = oauthProperties.getOk();
		final OkClient client = new OkClient(properties.getKey(), properties.getSecret(), properties.getPublicKey());
		this.initOAuth20Client(client, properties, ajaxRequestResolver, urlResolver);
		
		return client;
	}
	
	@Bean
	@ConditionalOnProperty(prefix = ShiroPac4jOAuthProperties.PREFIX, value = "orcid")
	public OrcidClient orcidClient(AjaxRequestResolver ajaxRequestResolver, UrlResolver urlResolver) {
		
		final ShiroPac4jOAuthClientProperties properties = oauthProperties.getOrcid();
		final OrcidClient client = new OrcidClient(properties.getKey(), properties.getSecret());
		this.initOAuth20Client(client, properties, ajaxRequestResolver, urlResolver);
		
		return client;
	}
	
	/*@Bean
	@ConditionalOnProperty(prefix = ShiroPac4jOAuthProperties.PREFIX, value = "oschina")
	public OschinaClient oschinaClient(AjaxRequestResolver ajaxRequestResolver, UrlResolver urlResolver) {
		
		final ShiroPac4jOAuthClientProperties properties = oauthProperties.getOschina();
		final OschinaClient client = new OschinaClient(properties.getKey(), properties.getSecret());
		this.initOAuth20Client(client, properties, ajaxRequestResolver, urlResolver);
		
		return client;
	}*/
	
	
	@Bean
	@ConditionalOnProperty(prefix = ShiroPac4jOAuthProperties.PREFIX, value = "paypal")
	public PayPalClient paypalClient(AjaxRequestResolver ajaxRequestResolver, UrlResolver urlResolver) {
		
		final ShiroPac4jOAuthClientProperties properties = oauthProperties.getPaypal();
		final PayPalClient client = new PayPalClient(properties.getKey(), properties.getSecret());
		this.initOAuth20Client(client, properties, ajaxRequestResolver, urlResolver);
		
		return client;
	}
	
	/*@Bean
	@ConditionalOnProperty(prefix = ShiroPac4jOAuthProperties.PREFIX, value = "qq")
	public QQClient qqClient(AjaxRequestResolver ajaxRequestResolver, UrlResolver urlResolver) {
		
		final ShiroPac4jOAuthClientProperties properties = oauthProperties.getQq();
		final QQClient client = new QQClient(properties.getKey(), properties.getSecret());
		this.initOAuth20Client(client, properties, ajaxRequestResolver, urlResolver);
		
		return client;
	}
	
	@Bean
	@ConditionalOnProperty(prefix = ShiroPac4jOAuthProperties.PREFIX, value = "renren")
	public BaiduClient renrenClient(AjaxRequestResolver ajaxRequestResolver, UrlResolver urlResolver) {
		
		final ShiroPac4jOAuthClientProperties properties = oauthProperties.getRenren();
		final BaiduClient client = new BaiduClient(properties.getKey(), properties.getSecret());
		this.initOAuth20Client(client, properties, ajaxRequestResolver, urlResolver);
		
		return client;
	}*/
	
	@Bean
	@ConditionalOnProperty(prefix = ShiroPac4jOAuthProperties.PREFIX, value = "strava")
	public StravaClient stravaClient(AjaxRequestResolver ajaxRequestResolver, UrlResolver urlResolver) {
		
		final ShiroPac4jOAuthStravaClientProperties properties = oauthProperties.getStrava();
		final StravaClient client = new StravaClient(properties.getKey(), properties.getSecret());
		this.initOAuth20Client(client, properties, ajaxRequestResolver, urlResolver);
		client.setApprovalPrompt(properties.getApprovalPrompt());
		
		return client;
	}
	
	@Bean
	@ConditionalOnProperty(prefix = ShiroPac4jOAuthProperties.PREFIX, value = "twitter")
	public TwitterClient twitterClient(AjaxRequestResolver ajaxRequestResolver, UrlResolver urlResolver) {
		
		final ShiroPac4jOAuthClientProperties properties = oauthProperties.getTwitter();
		final TwitterClient client = new TwitterClient(properties.getKey(), properties.getSecret());
		this.initOAuth10Client(client, properties, ajaxRequestResolver, urlResolver);
		
		return client;
		
	}
	
	@Bean
	@ConditionalOnProperty(prefix = ShiroPac4jOAuthProperties.PREFIX, value = "vk")
	public VkClient vkClient(AjaxRequestResolver ajaxRequestResolver, UrlResolver urlResolver) {
		
		final ShiroPac4jOAuthClientProperties properties = oauthProperties.getVk();
		final VkClient client = new VkClient(properties.getKey(), properties.getSecret());
		this.initOAuth20Client(client, properties, ajaxRequestResolver, urlResolver);
		
		return client;
	}
	
	@Bean
	@ConditionalOnProperty(prefix = ShiroPac4jOAuthProperties.PREFIX, value = "weibo")
	public SinaWeiboClient sinaWeiboClient(AjaxRequestResolver ajaxRequestResolver, UrlResolver urlResolver) {
		
		final ShiroPac4jOAuthClientProperties properties = oauthProperties.getWeibo();
		final SinaWeiboClient client = new SinaWeiboClient(properties.getKey(), properties.getSecret());
		this.initOAuth20Client(client, properties, ajaxRequestResolver, urlResolver);
		
		return client;
		
	}
	
	@Bean
	@ConditionalOnProperty(prefix = ShiroPac4jOAuthProperties.PREFIX, value = "weixin")
	public WeiXinClient weiXinClient(AjaxRequestResolver ajaxRequestResolver, UrlResolver urlResolver) {
		
		final ShiroPac4jOAuthClientProperties properties = oauthProperties.getWeixin();
		final WeiXinClient client = new WeiXinClient(properties.getKey(), properties.getSecret());
		this.initOAuth20Client(client, properties, ajaxRequestResolver, urlResolver);
		
		return client;
		
	}
	
	@Bean
	@ConditionalOnProperty(prefix = ShiroPac4jOAuthProperties.PREFIX, value = "windowslive")
	public WindowsLiveClient windowsliveClient(AjaxRequestResolver ajaxRequestResolver, UrlResolver urlResolver) {
		
		final ShiroPac4jOAuthClientProperties properties = oauthProperties.getWindowslive();
		final WindowsLiveClient client = new WindowsLiveClient(properties.getKey(), properties.getSecret());
		this.initOAuth20Client(client, properties, ajaxRequestResolver, urlResolver);
		
		return client;
	}
	
	@Bean
	@ConditionalOnProperty(prefix = ShiroPac4jOAuthProperties.PREFIX, value = "wordpress")
	public WordPressClient wordpressClient(AjaxRequestResolver ajaxRequestResolver, UrlResolver urlResolver) {
		
		final ShiroPac4jOAuthClientProperties properties = oauthProperties.getWordpress();
		final WordPressClient client = new WordPressClient(properties.getKey(), properties.getSecret());
		this.initOAuth20Client(client, properties, ajaxRequestResolver, urlResolver);
		
		return client;
	}
	
	@Bean
	@ConditionalOnProperty(prefix = ShiroPac4jOAuthProperties.PREFIX, value = "yahoo")
	public YahooClient yahooClient(AjaxRequestResolver ajaxRequestResolver, UrlResolver urlResolver) {
		
		final ShiroPac4jOAuthClientProperties properties = oauthProperties.getYahoo();
		final YahooClient client = new YahooClient(properties.getKey(), properties.getSecret());
		this.initOAuth10Client(client, properties, ajaxRequestResolver, urlResolver);
		
		return client;
	}
	
	protected <U extends OAuth10Profile> void initOAuth10Client(OAuth10Client<U> client,
			ShiroPac4jOAuthClientProperties properties, AjaxRequestResolver ajaxRequestResolver,
			UrlResolver urlResolver) {

		final OAuth10Configuration configuration = client.getConfiguration();
		
		//configuration.setConnectTimeout(properties.getConnectTimeout());
		//configuration.setHasGrantType(properties.isHasGrantType());
		//configuration.setReadTimeout(properties.getReadTimeout());
		configuration.setResponseType(properties.getResponseType());
		configuration.setTokenAsHeader(properties.isTokenAsHeader());
		
		client.setName(properties.getName());
		client.setAjaxRequestResolver(ajaxRequestResolver);
		client.setCallbackUrl(pac4jProperties.getCallbackUrl());
		client.setKey(properties.getKey());
		client.setConfiguration(configuration);
		//client.setIncludeClientNameInCallbackUrl(pac4jProperties.isIncludeClientNameInCallbackUrl());
		client.setSecret(properties.getSecret());
		client.setUrlResolver(urlResolver);
		
	}

	protected <U extends OAuth20Profile> void initOAuth20Client(OAuth20Client<U> client,
			ShiroPac4jOAuthClientProperties properties, AjaxRequestResolver ajaxRequestResolver,
			UrlResolver urlResolver) {

		final OAuth20Configuration configuration = client.getConfiguration();

		//configuration.setConnectTimeout(properties.getConnectTimeout());
		configuration.setCustomParams(properties.getCustomParams());
		//configuration.setHasGrantType(properties.isHasGrantType());
		//configuration.setReadTimeout(properties.getReadTimeout());
		configuration.setScope(properties.getScope());
		configuration.setResponseType(properties.getResponseType());
		configuration.setWithState(properties.isWithState());
		//configuration.setStateData(properties.getStateData());
		configuration.setTokenAsHeader(properties.isTokenAsHeader());

		client.setName(properties.getName());
		client.setAjaxRequestResolver(ajaxRequestResolver);
		client.setCallbackUrl(pac4jProperties.getCallbackUrl());
		client.setKey(properties.getKey());
		client.setConfiguration(configuration);
		//client.setIncludeClientNameInCallbackUrl(pac4jProperties.isIncludeClientNameInCallbackUrl());
		client.setSecret(properties.getSecret());
		client.setUrlResolver(urlResolver);
	}
	
}
