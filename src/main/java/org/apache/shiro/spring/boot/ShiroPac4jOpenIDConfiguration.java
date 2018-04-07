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

import org.pac4j.openid.client.YahooOpenIdClient;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
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
@ConditionalOnClass({ YahooOpenIdClient.class})
@ConditionalOnProperty(prefix = ShiroPac4jOpenIDProperties.PREFIX, value = "enabled", havingValue = "true")
@EnableConfigurationProperties({ ShiroPac4jOpenIDProperties.class, ShiroBizProperties.class, ServerProperties.class })
public class ShiroPac4jOpenIDConfiguration {

	@Bean
 	@ConditionalOnProperty(prefix = ShiroPac4jOpenIDProperties.PREFIX, value = "casClient", havingValue = "true")
 	public YahooOpenIdClient yahooOpenIdClient() {
 		
 	    final YahooOpenIdClient openIdClient = new YahooOpenIdClient();
 	  
 		return openIdClient;
 	}
		
}
