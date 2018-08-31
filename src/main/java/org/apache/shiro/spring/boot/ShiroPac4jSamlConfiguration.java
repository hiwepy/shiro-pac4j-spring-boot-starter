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

import java.io.File;

import org.pac4j.core.http.url.UrlResolver;
import org.pac4j.saml.client.SAML2Client;
import org.pac4j.saml.client.SAML2ClientConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

@Configuration
@AutoConfigureBefore( name = {
	"org.apache.shiro.spring.boot.ShiroPac4jClientsConfiguration"
})
@ConditionalOnWebApplication
@ConditionalOnClass({ SAML2Client.class, SAML2ClientConfiguration.class})
@ConditionalOnProperty(prefix = ShiroPac4jSamlProperties.PREFIX, value = "enabled", havingValue = "true")
@EnableConfigurationProperties({ ShiroPac4jSamlProperties.class, ShiroBizProperties.class, ServerProperties.class })
public class ShiroPac4jSamlConfiguration {

	@Bean
    public SAML2ClientConfiguration samlConfiguration() {
		
		final SAML2ClientConfiguration configuration = new SAML2ClientConfiguration(new ClassPathResource("samlKeystore.jks"),
                "pac4j-demo-passwd",
                "pac4j-demo-passwd",
                new ClassPathResource("metadata-okta.xml"));
        configuration.setMaximumAuthenticationLifetime(3600);
        configuration.setServiceProviderEntityId("http://localhost:8080/callback?client_name=SAML2Client");
        configuration.setServiceProviderMetadataResource(new FileSystemResource(new File("sp-metadata.xml").getAbsoluteFile()));
	    
	    /*configuration.setCallbackUrl(callbackUrl);
	    configuration.setClientAuthenticationMethod(clientAuthenticationMethod);
	    configuration.setClientAuthenticationMethodAsString(auth);
	    configuration.setClientId(clientId);
	    configuration.setConnectTimeout(connectTimeout);
	    configuration.setCustomParams(customParams);
	    configuration.setDiscoveryURI(discoveryURI);
	    configuration.setLogoutUrl(logoutUrl);
	    configuration.setMaxClockSkew(maxClockSkew);
	    configuration.setPreferredJwsAlgorithm(preferredJwsAlgorithm);
	    configuration.setProviderMetadata(providerMetadata);
	    configuration.setReadTimeout(readTimeout);
	    configuration.setResourceRetriever(resourceRetriever);
	    configuration.setResponseMode(responseMode);
	    configuration.setResponseType(responseType);
	    configuration.setScope(scope);
	    configuration.setSecret(secret);
	    configuration.setUseNonce(useNonce);*/
		
		return configuration;
	}
	
	@Bean
	@ConditionalOnProperty(prefix = ShiroPac4jSamlProperties.PREFIX, value = "casClient", havingValue = "true")
	public SAML2Client saml2Client(SAML2ClientConfiguration samlConfiguration, UrlResolver urlResolver) {
		
	    final SAML2Client saml2Client = new SAML2Client(samlConfiguration);
	    
	    /*saml2Client.setAjaxRequestResolver(ajaxRequestResolver);
	    saml2Client.setAuthenticator(authenticator);
	    saml2Client.setAuthorizationGenerator(authorizationGenerator);
	    saml2Client.setAuthorizationGenerators(authorizationGenerators);
	    saml2Client.setAuthorizationGenerators(authorizationGenerators);
	    saml2Client.setCallbackUrl(callbackUrl);
	    saml2Client.setConfiguration(configuration);
	    saml2Client.setCredentialsExtractor(credentialsExtractor);
	    saml2Client.setIncludeClientNameInCallbackUrl(includeClientNameInCallbackUrl);
	    saml2Client.setLogoutActionBuilder(logoutActionBuilder);
	    saml2Client.setProfileCreator(profileCreator);
	    saml2Client.setRedirectActionBuilder(redirectActionBuilder);*/
	    saml2Client.setUrlResolver(urlResolver);
	    
		return saml2Client;
	}
	
    
    
    
}
