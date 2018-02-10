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
package org.apache.shiro.spring.boot.pac4j.property;

public class ShiroPac4jOAuthCasClientProperties extends ShiroPac4jOAuthClientProperties {

	 /**
     * The CAS OAuth server url (without a trailing slash).
     * For example: http://localhost:8080/cas/oauth2.0
     */
    private String casOAuthUrl;

    private String casLogoutUrl;

    private boolean springSecurityCompliant = false;

    private boolean implicitFlow = false;

	public String getCasOAuthUrl() {
		return casOAuthUrl;
	}

	public void setCasOAuthUrl(String casOAuthUrl) {
		this.casOAuthUrl = casOAuthUrl;
	}

	public String getCasLogoutUrl() {
		return casLogoutUrl;
	}

	public void setCasLogoutUrl(String casLogoutUrl) {
		this.casLogoutUrl = casLogoutUrl;
	}

	public boolean isSpringSecurityCompliant() {
		return springSecurityCompliant;
	}

	public void setSpringSecurityCompliant(boolean springSecurityCompliant) {
		this.springSecurityCompliant = springSecurityCompliant;
	}

	public boolean isImplicitFlow() {
		return implicitFlow;
	}

	public void setImplicitFlow(boolean implicitFlow) {
		this.implicitFlow = implicitFlow;
	}

}
