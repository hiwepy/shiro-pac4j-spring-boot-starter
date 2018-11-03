/*
 * Copyright (c) 2018, vindell (https://github.com/vindell).
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
import org.apache.shiro.spring.boot.pac4j.utils.Pac4jUrlUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class Pac4jPathBuilder {
	
	@Autowired
	private ShiroBizProperties bizProperties;
	@Autowired
	private ShiroPac4jProperties pac4jProperties;
	@Autowired
	private ShiroPac4jCasProperties casProperties;
	
	public String getLoginURL(String contextPath) {
		// 如果是Cas认证，则构造Cas登录跳转地址
		if(casProperties != null && (casProperties.isCasClient() || casProperties.isDirectCasClient() || casProperties.isDirectCasProxyClient())) {
			// 回调URL, i.e. /callback?client_name=cas
			String callbackUrl = Pac4jUrlUtils.constructCallbackUrl(pac4jProperties);
			// 完整的Cas登录URL,i.e. https://localhost:8443/cas/login?service=https://localhost:8080/myapp/callback?client_name=cas
			return CasUrlUtils.constructLoginRedirectUrl(casProperties, contextPath, callbackUrl);
		}
		// 常规的登录地址
		return Pac4jUrlUtils.constructCallbackUrl(contextPath, bizProperties.getLoginUrl());
	}
	
	public String getLogoutURL(String contextPath) {
		// 如果是Cas认证，则构造Cas注销跳转地址：注销后进入Cas登录界面，登录后重新跳转回来进行cas认证
		if(casProperties != null && (casProperties.isCasClient() || casProperties.isDirectCasClient() || casProperties.isDirectCasProxyClient())) {
			// 回调URL, i.e. /callback?client_name=cas
			String callbackUrl = Pac4jUrlUtils.constructCallbackUrl(pac4jProperties);
			// 完整的Cas登录URL,i.e. https://localhost:8443/cas/login?service=https://localhost:8080/myapp/callback?client_name=cas
			return CasUrlUtils.constructLoginRedirectUrl(casProperties, contextPath, callbackUrl);
		}
		// 常规的注销地址：注销后进入本地登录页面
		return Pac4jUrlUtils.constructCallbackUrl(contextPath, bizProperties.getLoginUrl());
	}

}
