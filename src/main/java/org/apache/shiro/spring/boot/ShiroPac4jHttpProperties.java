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

import org.pac4j.core.context.Pac4jConstants;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(ShiroPac4jHttpProperties.PREFIX)
public class ShiroPac4jHttpProperties {

	public static final String PREFIX = "shiro.pac4j.http";
	
	/** Whether Enable Pac4j Http. */
	private boolean enabled = false;
	/** 登录地址：会话不存在时访问的地址 */
	private String loginUrl;
	
    private String usernameParameter = Pac4jConstants.USERNAME;
    private String passwordParameter = Pac4jConstants.PASSWORD;
	
    /** DirectCasProxyClient */
    
    private boolean formClient = false;
    private String formClientName = "form";
    
    /** IndirectBasicAuthClient */
    
    private boolean indirectBasicAuthClient = false;
    private String indirectBasicAuthClientName = "indirect-basic-auth";
    private String realmName;
    
    /** DirectBasicAuthClient */
    
    private boolean directBasicAuthClient = false;
    private String directBasicAuthClientName = "direct-basic-auth";

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public String getUsernameParameter() {
		return usernameParameter;
	}

	public void setUsernameParameter(String usernameParameter) {
		this.usernameParameter = usernameParameter;
	}

	public String getPasswordParameter() {
		return passwordParameter;
	}

	public void setPasswordParameter(String passwordParameter) {
		this.passwordParameter = passwordParameter;
	}

	public boolean isFormClient() {
		return formClient;
	}

	public void setFormClient(boolean formClient) {
		this.formClient = formClient;
	}

	public String getFormClientName() {
		return formClientName;
	}

	public void setFormClientName(String formClientName) {
		this.formClientName = formClientName;
	}

	public boolean isIndirectBasicAuthClient() {
		return indirectBasicAuthClient;
	}

	public void setIndirectBasicAuthClient(boolean indirectBasicAuthClient) {
		this.indirectBasicAuthClient = indirectBasicAuthClient;
	}

	public String getIndirectBasicAuthClientName() {
		return indirectBasicAuthClientName;
	}

	public void setIndirectBasicAuthClientName(String indirectBasicAuthClientName) {
		this.indirectBasicAuthClientName = indirectBasicAuthClientName;
	}
	
	public String getRealmName() {
		return realmName;
	}

	public void setRealmName(String realmName) {
		this.realmName = realmName;
	}

	public boolean isDirectBasicAuthClient() {
		return directBasicAuthClient;
	}

	public void setDirectBasicAuthClient(boolean directBasicAuthClient) {
		this.directBasicAuthClient = directBasicAuthClient;
	}

	public String getDirectBasicAuthClientName() {
		return directBasicAuthClientName;
	}

	public void setDirectBasicAuthClientName(String directBasicAuthClientName) {
		this.directBasicAuthClientName = directBasicAuthClientName;
	}

}
