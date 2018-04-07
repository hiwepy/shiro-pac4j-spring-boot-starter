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
package org.apache.shiro.spring.boot.pac4j.property;

import org.pac4j.oauth.client.FacebookClient;
import org.pac4j.oauth.profile.facebook.FacebookProfileDefinition;

public class ShiroPac4jOAuthFacebookClientProperties extends ShiroPac4jOAuthClientProperties {

	protected String fields = FacebookClient.DEFAULT_FIELDS;
	protected int limit = FacebookProfileDefinition.DEFAULT_LIMIT;
	protected boolean requiresExtendedToken = false;
	protected boolean useAppsecretProof = false;

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public boolean isRequiresExtendedToken() {
		return requiresExtendedToken;
	}

	public void setRequiresExtendedToken(boolean requiresExtendedToken) {
		this.requiresExtendedToken = requiresExtendedToken;
	}

	public boolean isUseAppsecretProof() {
		return useAppsecretProof;
	}

	public void setUseAppsecretProof(boolean useAppsecretProof) {
		this.useAppsecretProof = useAppsecretProof;
	}

}
