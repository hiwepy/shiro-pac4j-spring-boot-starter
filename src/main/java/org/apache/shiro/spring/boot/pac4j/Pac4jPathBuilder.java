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
package org.apache.shiro.spring.boot.pac4j;

import org.apache.shiro.spring.boot.ShiroBizProperties;
import org.apache.shiro.spring.boot.ShiroPac4jCasProperties;
import org.apache.shiro.spring.boot.ShiroPac4jProperties;
import org.apache.shiro.spring.boot.pac4j.utils.CasUrlUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class Pac4jPathBuilder {
	
	@Autowired
	private ShiroBizProperties bizProperties;
	@Autowired
	private ShiroPac4jProperties pac4jProperties;
	@Autowired
	private ShiroPac4jCasProperties casProperties;
	
	public String getLoginURL(String contextPath) {
		
		if(casProperties != null && casProperties.isCasClient()) {
			return CasUrlUtils.constructLoginRedirectUrl(casProperties, contextPath, CasUrlUtils.constructCallbackUrl(casProperties, pac4jProperties));
		}
		
		return null;
	}
	
	public String getLogoutURL(String contextPath) {
		
		if(casProperties != null && casProperties.isCasClient()) {
			return CasUrlUtils.constructLogoutRedirectUrl(casProperties, contextPath, bizProperties.getLoginUrl());
		}
		
		return null;
	}

}
