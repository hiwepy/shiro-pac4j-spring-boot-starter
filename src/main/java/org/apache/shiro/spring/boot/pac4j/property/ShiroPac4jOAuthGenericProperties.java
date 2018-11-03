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
package org.apache.shiro.spring.boot.pac4j.property;


import java.util.HashMap;
import java.util.Map;

import org.pac4j.oauth.config.OAuth20Configuration;

import com.github.scribejava.core.model.Verb;

public class ShiroPac4jOAuthGenericProperties extends OAuth20Configuration {

	private String name;
	private String desc;
	private String authUrl;
	private String logoUrl;
	private String tokenUrl;
	private String profileUrl;
	private String profilePath;
	private Verb profileVerb = Verb.POST;
	private Map<String, String> profileAttrs = new HashMap<String, String>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}


	public String getAuthUrl() {
		return authUrl;
	}

	public void setAuthUrl(String authUrl) {
		this.authUrl = authUrl;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getTokenUrl() {
		return tokenUrl;
	}

	public void setTokenUrl(String tokenUrl) {
		this.tokenUrl = tokenUrl;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

	public String getProfilePath() {
		return profilePath;
	}

	public void setProfilePath(String profilePath) {
		this.profilePath = profilePath;
	}

	public Verb getProfileVerb() {
		return profileVerb;
	}

	public void setProfileVerb(Verb profileVerb) {
		this.profileVerb = profileVerb;
	}

	public Map<String, String> getProfileAttrs() {
		return profileAttrs;
	}

	public void setProfileAttrs(Map<String, String> profileAttrs) {
		this.profileAttrs = profileAttrs;
	}

}
