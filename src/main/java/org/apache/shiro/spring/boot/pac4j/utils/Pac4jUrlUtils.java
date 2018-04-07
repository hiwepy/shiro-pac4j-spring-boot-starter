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
package org.apache.shiro.spring.boot.pac4j.utils;

import org.apache.shiro.spring.boot.ShiroPac4jProperties;
import org.apache.shiro.spring.boot.utils.StringUtils;

public class Pac4jUrlUtils {

	/**
	 * 
	 * @description	： 构造回调URL, i.e. myapp/serverUrl
	 * @author 		： <a href="https://github.com/vindell">vindell</a>
	 * @date 		：2018年2月9日 上午9:38:23
	 * @param contextPath
	 * @param serverUrl
	 * @return
	 */
	public static String constructCallbackUrl(String contextPath, String serverUrl) {
		contextPath = StringUtils.hasText(contextPath) ? contextPath : "/";
		if (contextPath.endsWith("/")) {
			contextPath = contextPath.substring(0, contextPath.length() - 1);
		}
		StringBuilder callbackUrlBuilder = new StringBuilder(contextPath).append(serverUrl);
		return callbackUrlBuilder.toString();
	}
	
	/**
	 * 
	 * @description	： 构造回调URL, i.e. /callback?client_name=cas
	 * @author 		： <a href="https://github.com/vindell">vindell</a>
	 * @date 		：2018年2月9日 上午9:35:29
	 * @param pac4jProperties
	 * @return
	 */
	public static String constructCallbackUrl(ShiroPac4jProperties pac4jProperties) {
		String callbackUrl = pac4jProperties.getCallbackUrl();
		StringBuilder callbackUrlBuilder = new StringBuilder(callbackUrl).append((callbackUrl.contains("?") ? "&" : "?")).append(pac4jProperties.getClientParameterName()).append("=").append(pac4jProperties.getClientName());
		return callbackUrlBuilder.toString();
	}
	
}
