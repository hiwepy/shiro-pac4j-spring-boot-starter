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

import io.buji.pac4j.token.Pac4jToken;
import org.apache.shiro.authc.HostAuthenticationToken;
import org.apache.shiro.authc.RememberMeAuthenticationToken;
import org.apache.shiro.biz.authc.token.LoginType;
import org.apache.shiro.biz.authc.token.LoginTypeAuthenticationToken;
import org.pac4j.core.profile.CommonProfile;

import java.util.List;

/**
 * <p>Token for Pac4j Cas.</p>
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@SuppressWarnings("serial")
public class Pac4jCasToken extends Pac4jToken implements HostAuthenticationToken, RememberMeAuthenticationToken,
		LoginTypeAuthenticationToken {

	/** The service ticket returned by the CAS server */
    private String ticket = null;
	/** 用户名 */
	private String username;
	/** 登陆IP */
	private String host;

	public Pac4jCasToken(String host, final List<CommonProfile> profiles, final boolean isRemembered) {
		super(profiles, isRemembered);
		this.host = host;
	}

	@Override
	/**
	 * Returns the principal.
	 *
	 * @return the principal
	 */
	public Object getPrincipal() {
		return username;
	}

	@Override
	/**
	 * Returns the credentials.
	 *
	 * @return the credentials
	 */
	public Object getCredentials() {
		return ticket;
	}

	@Override
	/**
	 * Returns the host.
	 *
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	@Override
	/**
	 * Returns the login type.
	 *
	 * @return the login type
	 */
	public LoginType getLoginType() {
		return LoginType.CAS;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Sets the host.
	 *
	 * @param host the host
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * Returns the ticket.
	 *
	 * @return the ticket
	 */
	public String getTicket() {
		return ticket;
	}

	/**
	 * Sets the ticket.
	 *
	 * @param ticket the ticket
	 */
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

}
