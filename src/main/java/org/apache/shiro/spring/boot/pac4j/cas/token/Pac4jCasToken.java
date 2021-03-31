/*
 * Copyright (c) 2018, hiwepy (https://github.com/hiwepy).
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
package org.apache.shiro.spring.boot.pac4j.cas.token;

import java.util.List;

import org.apache.shiro.authc.HostAuthenticationToken;
import org.apache.shiro.authc.RememberMeAuthenticationToken;
import org.apache.shiro.biz.authc.token.LoginType;
import org.apache.shiro.biz.authc.token.LoginTypeAuthenticationToken;
import org.pac4j.core.profile.UserProfile;

import io.buji.pac4j.token.Pac4jToken;

@SuppressWarnings("serial")
public class Pac4jCasToken extends Pac4jToken implements HostAuthenticationToken, RememberMeAuthenticationToken,
		LoginTypeAuthenticationToken {

	/** The service ticket returned by the CAS server */
    private String ticket = null;
	/** 用户名 */
	private String username;
	/** 登陆IP */
	private String host;

	public Pac4jCasToken(String host, final List<UserProfile> profiles, final boolean isRemembered) {
		super(profiles, isRemembered);
		this.host = host;
	}

	@Override
	public Object getPrincipal() {
		return username;
	}

	@Override
	public Object getCredentials() {
		return ticket;
	}

	@Override
	public String getHost() {
		return host;
	}

	@Override
	public LoginType getLoginType() {
		return LoginType.CAS;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

}
