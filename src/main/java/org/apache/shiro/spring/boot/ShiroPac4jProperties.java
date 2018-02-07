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

import org.jasig.cas.client.Protocol;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(ShiroPac4jProperties.PREFIX)
public class ShiroPac4jProperties {

	public static final String PREFIX = "cas";

	public static enum IgnoreUrlPatternType {
		Contains {
			@Override
			public String toString() {
				return "CONTAINS";
			}
		},
		Regex {
			@Override
			public String toString() {
				return "REGEX";
			}
		},
		Exact {
			@Override
			public String toString() {
				return "EXACT";
			}
		}
	}

	/**
	 * Enable Cas.
	 */
	private boolean enabled = false;
	/**
	 * DEFAULT,JNDI,WEB_XML,PROPERTY_FILE,SYSTEM_PROPERTIES
	 */
	private String configurationStrategy;

	/**
	 * Defines the location of the CAS server login URL, i.e.
	 * https://localhost:8443/cas/login
	 */
	private String casServerLoginUrl;
	/** The prefix url of the CAS server. */
	private String casServerUrlPrefix;

	private boolean eagerlyCreateSessions = true;

	private boolean acceptAnyProxy = true;
	private String allowedProxyChains;
	/**
	 * Specifies the name of the request parameter on where to find the artifact
	 * (i.e. ticket).
	 */
	private String artifactParameterName;
	private boolean artifactParameterOverPost = false;
	private String[] assertionThreadLocalFilterUrlPatterns = new String[] { "/*" };
	private String authenticationRedirectStrategyClass;
	private String[] authenticationFilterUrlPatterns = new String[] { "/*" };

	private String cipherAlgorithm;
	/**
	 * Sets where response.encodeUrl should be called on service urls when
	 * constructed.
	 */
	private boolean encodeServiceUrl = true;

	private String encoding;

	private boolean exceptionOnValidationFailure = true;

	/**
	 * Whether to send the gateway request or not. Valid values are eithertrue/false
	 * (or no value at all). Note that renew cannot be specified as local
	 * init-paramsetting.
	 */
	private boolean gateway = false;
	private String gatewayStorageClass;

	private String hostnameVerifier;
	private String hostnameVerifierConfig;

	private boolean ignoreCase = false;
	private String ignorePattern;
	private IgnoreUrlPatternType ignoreUrlPatternType = IgnoreUrlPatternType.Regex;

	private boolean ignoreInitConfiguration = false;
	private String logoutParameterName;

	private long millisBetweenCleanUps = 60000L;
	/**
	 * The protocol of the CAS Client.
	 */
	private Protocol protocol = Protocol.CAS2;
	private String proxyCallbackUrl;
	private String proxyReceptorUrl;
	private String proxyGrantingTicketStorageClass;
	private String[] requestWrapperFilterUrlPatterns = new String[] { "/*" };
	private boolean redirectAfterValidation = true;
	/**
	 * Whether to send the renew request or not. Valid values are eithertrue/false
	 * (or no value at all). Note that renew cannot be specified as local
	 * init-paramsetting.
	 */
	private boolean renew = false;
	/** Name of parameter containing the state of the CAS server webflow. */
	private String relayStateParameterName;
	private String roleAttribute;
	
	private String secretKey;

	/** The exact url of the service. */
	private String serviceUrl;
	/**
     * The name of the server.  Should be in the following format: {protocol}:{hostName}:{port}.
     * Standard ports can be excluded. 
     */
	private String serverName;
	private String[] signOutFilterUrlPatterns = new String[] { "/*" };
	private String sslConfigFile;

	private String[] ticketValidationFilterUrlPatterns = new String[] { "/*" };
	private String ticketValidatorClass;
	private long tolerance = 1000L;

	private boolean useSession = true;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getConfigurationStrategy() {
		return configurationStrategy;
	}

	public void setConfigurationStrategy(String configurationStrategy) {
		this.configurationStrategy = configurationStrategy;
	}

	public String getCasServerLoginUrl() {
		return casServerLoginUrl;
	}

	public void setCasServerLoginUrl(String casServerLoginUrl) {
		this.casServerLoginUrl = casServerLoginUrl;
	}

	public String getCasServerUrlPrefix() {
		return casServerUrlPrefix;
	}

	public void setCasServerUrlPrefix(String casServerUrlPrefix) {
		this.casServerUrlPrefix = casServerUrlPrefix;
	}

	public boolean isEagerlyCreateSessions() {
		return eagerlyCreateSessions;
	}

	public void setEagerlyCreateSessions(boolean eagerlyCreateSessions) {
		this.eagerlyCreateSessions = eagerlyCreateSessions;
	}

	public boolean isAcceptAnyProxy() {
		return acceptAnyProxy;
	}

	public void setAcceptAnyProxy(boolean acceptAnyProxy) {
		this.acceptAnyProxy = acceptAnyProxy;
	}

	public String getAllowedProxyChains() {
		return allowedProxyChains;
	}

	public void setAllowedProxyChains(String allowedProxyChains) {
		this.allowedProxyChains = allowedProxyChains;
	}

	public String getArtifactParameterName() {
		return artifactParameterName;
	}

	public void setArtifactParameterName(String artifactParameterName) {
		this.artifactParameterName = artifactParameterName;
	}

	public boolean isArtifactParameterOverPost() {
		return artifactParameterOverPost;
	}

	public void setArtifactParameterOverPost(boolean artifactParameterOverPost) {
		this.artifactParameterOverPost = artifactParameterOverPost;
	}

	public String[] getAssertionThreadLocalFilterUrlPatterns() {
		return assertionThreadLocalFilterUrlPatterns;
	}

	public void setAssertionThreadLocalFilterUrlPatterns(String[] assertionThreadLocalFilterUrlPatterns) {
		this.assertionThreadLocalFilterUrlPatterns = assertionThreadLocalFilterUrlPatterns;
	}

	public String getAuthenticationRedirectStrategyClass() {
		return authenticationRedirectStrategyClass;
	}

	public void setAuthenticationRedirectStrategyClass(String authenticationRedirectStrategyClass) {
		this.authenticationRedirectStrategyClass = authenticationRedirectStrategyClass;
	}

	public String[] getAuthenticationFilterUrlPatterns() {
		return authenticationFilterUrlPatterns;
	}

	public void setAuthenticationFilterUrlPatterns(String[] authenticationFilterUrlPatterns) {
		this.authenticationFilterUrlPatterns = authenticationFilterUrlPatterns;
	}

	public String getCipherAlgorithm() {
		return cipherAlgorithm;
	}

	public void setCipherAlgorithm(String cipherAlgorithm) {
		this.cipherAlgorithm = cipherAlgorithm;
	}

	public boolean isEncodeServiceUrl() {
		return encodeServiceUrl;
	}

	public void setEncodeServiceUrl(boolean encodeServiceUrl) {
		this.encodeServiceUrl = encodeServiceUrl;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public boolean isExceptionOnValidationFailure() {
		return exceptionOnValidationFailure;
	}

	public void setExceptionOnValidationFailure(boolean exceptionOnValidationFailure) {
		this.exceptionOnValidationFailure = exceptionOnValidationFailure;
	}

	public boolean isGateway() {
		return gateway;
	}

	public void setGateway(boolean gateway) {
		this.gateway = gateway;
	}

	public String getGatewayStorageClass() {
		return gatewayStorageClass;
	}

	public void setGatewayStorageClass(String gatewayStorageClass) {
		this.gatewayStorageClass = gatewayStorageClass;
	}

	public String getHostnameVerifier() {
		return hostnameVerifier;
	}

	public void setHostnameVerifier(String hostnameVerifier) {
		this.hostnameVerifier = hostnameVerifier;
	}

	public String getHostnameVerifierConfig() {
		return hostnameVerifierConfig;
	}

	public void setHostnameVerifierConfig(String hostnameVerifierConfig) {
		this.hostnameVerifierConfig = hostnameVerifierConfig;
	}

	public boolean isIgnoreCase() {
		return ignoreCase;
	}

	public void setIgnoreCase(boolean ignoreCase) {
		this.ignoreCase = ignoreCase;
	}

	public String getIgnorePattern() {
		return ignorePattern;
	}

	public void setIgnorePattern(String ignorePattern) {
		this.ignorePattern = ignorePattern;
	}

	public IgnoreUrlPatternType getIgnoreUrlPatternType() {
		return ignoreUrlPatternType;
	}

	public void setIgnoreUrlPatternType(IgnoreUrlPatternType ignoreUrlPatternType) {
		this.ignoreUrlPatternType = ignoreUrlPatternType;
	}

	public boolean isIgnoreInitConfiguration() {
		return ignoreInitConfiguration;
	}

	public void setIgnoreInitConfiguration(boolean ignoreInitConfiguration) {
		this.ignoreInitConfiguration = ignoreInitConfiguration;
	}

	public String getLogoutParameterName() {
		return logoutParameterName;
	}

	public void setLogoutParameterName(String logoutParameterName) {
		this.logoutParameterName = logoutParameterName;
	}

	public long getMillisBetweenCleanUps() {
		return millisBetweenCleanUps;
	}

	public void setMillisBetweenCleanUps(long millisBetweenCleanUps) {
		this.millisBetweenCleanUps = millisBetweenCleanUps;
	}

	public Protocol getProtocol() {
		return protocol;
	}

	public void setProtocol(Protocol protocol) {
		this.protocol = protocol;
	}

	public String getProxyCallbackUrl() {
		return proxyCallbackUrl;
	}

	public void setProxyCallbackUrl(String proxyCallbackUrl) {
		this.proxyCallbackUrl = proxyCallbackUrl;
	}

	public String getProxyReceptorUrl() {
		return proxyReceptorUrl;
	}

	public void setProxyReceptorUrl(String proxyReceptorUrl) {
		this.proxyReceptorUrl = proxyReceptorUrl;
	}

	public String getProxyGrantingTicketStorageClass() {
		return proxyGrantingTicketStorageClass;
	}

	public void setProxyGrantingTicketStorageClass(String proxyGrantingTicketStorageClass) {
		this.proxyGrantingTicketStorageClass = proxyGrantingTicketStorageClass;
	}

	public String[] getRequestWrapperFilterUrlPatterns() {
		return requestWrapperFilterUrlPatterns;
	}

	public void setRequestWrapperFilterUrlPatterns(String[] requestWrapperFilterUrlPatterns) {
		this.requestWrapperFilterUrlPatterns = requestWrapperFilterUrlPatterns;
	}

	public boolean isRedirectAfterValidation() {
		return redirectAfterValidation;
	}

	public void setRedirectAfterValidation(boolean redirectAfterValidation) {
		this.redirectAfterValidation = redirectAfterValidation;
	}

	public boolean isRenew() {
		return renew;
	}

	public void setRenew(boolean renew) {
		this.renew = renew;
	}

	public String getRelayStateParameterName() {
		return relayStateParameterName;
	}

	public void setRelayStateParameterName(String relayStateParameterName) {
		this.relayStateParameterName = relayStateParameterName;
	}

	public String getRoleAttribute() {
		return roleAttribute;
	}

	public void setRoleAttribute(String roleAttribute) {
		this.roleAttribute = roleAttribute;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getServiceUrl() {
		return serviceUrl;
	}

	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String[] getSignOutFilterUrlPatterns() {
		return signOutFilterUrlPatterns;
	}

	public void setSignOutFilterUrlPatterns(String[] signOutFilterUrlPatterns) {
		this.signOutFilterUrlPatterns = signOutFilterUrlPatterns;
	}

	public String getSslConfigFile() {
		return sslConfigFile;
	}

	public void setSslConfigFile(String sslConfigFile) {
		this.sslConfigFile = sslConfigFile;
	}

	public String[] getTicketValidationFilterUrlPatterns() {
		return ticketValidationFilterUrlPatterns;
	}

	public void setTicketValidationFilterUrlPatterns(String[] ticketValidationFilterUrlPatterns) {
		this.ticketValidationFilterUrlPatterns = ticketValidationFilterUrlPatterns;
	}

	public String getTicketValidatorClass() {
		return ticketValidatorClass;
	}

	public void setTicketValidatorClass(String ticketValidatorClass) {
		this.ticketValidatorClass = ticketValidatorClass;
	}

	public long getTolerance() {
		return tolerance;
	}

	public void setTolerance(long tolerance) {
		this.tolerance = tolerance;
	}

	public boolean isUseSession() {
		return useSession;
	}

	public void setUseSession(boolean useSession) {
		this.useSession = useSession;
	}

}
