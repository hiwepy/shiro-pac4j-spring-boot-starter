package org.apache.shiro.spring.boot;

import org.pac4j.cas.config.CasProtocol;
import org.pac4j.core.context.HttpConstants;
import org.pac4j.core.context.Pac4jConstants;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(ShiroPac4jCasProperties.PREFIX)
public class ShiroPac4jCasProperties {

	public static final String PREFIX = "shiro.pac4j.cas";

	/* ================================== Cas Client ================================= */
	
	// default name of the CAS attribute for remember me authentication (CAS 3.4.10+)
    public static final String DEFAULT_REMEMBER_ME_ATTRIBUTE_NAME = "longTermAuthenticationRequestTokenUsed";
	
	/** Defines the location of the CAS server login URL, i.e. https://localhost:8443/cas/login */
	private String casServerLoginUrl;
	/** Defines the location of the CAS server logout URL, i.e. https://localhost:8443/cas/logout */
	private String casServerLogoutUrl;
	/** Defines the location of the CAS server rest URL, i.e. https://localhost:8443/cas/v1/tickets */
	private String casServerRestUrl;
	/** The prefix url of the CAS server. i.e.https://localhost:8443/cas */
	private String casServerUrlPrefix;
    /** Specifies whether any proxy is OK. Defaults to false. */
	private boolean acceptAnyProxy = false;
	/**
	 * Specifies the proxy chain. 
	 * Each acceptable proxy chain should include a space-separated list of URLs (for exact match) or 
	 * regular expressions of URLs (starting by the ^ character). 
	 * Each acceptable proxy chain should appear on its own line.
	 */
	private String allowedProxyChains;
	/** Specifies the name of the request parameter on where to find the artifact (i.e. ticket). */
	private String artifactParameterName = "ticket";
	/** Specifies the encoding charset the client should use */
	private String encoding = "UTF-8";
	/** Whether the client should auto encode the service url. Defaults to true */
	private boolean encodeServiceUrl = true;
	/** Specifies whether gateway=true should be sent to the CAS server. Valid values are either true/false (or no value at all) */
	private boolean gateway = false;
	/**
	 * Specifies whether renew=true should be sent to the CAS server. 
	 * Valid values are either true/false (or no value at all). 
	 * Note that renew cannot be specified as local init-param setting..
	 */
	private boolean renew = false;
	/**
	 * The name of the server this application is hosted on. 
	 * Service URL will be dynamically constructed using this, 
	 * i.e. https://localhost:8443 (you must include the protocol, but port is optional if it's a standard port).
	 * 此处为应用服务器地址,http://ip:端口即可
	 */
	private String serverName;
	/** Defines the location of the application cas callback URL, i.e. /callback */
	//private String serverCallbackUrl;
	/** The service URL to send to the CAS server, i.e. https://localhost:8443/yourwebapp/index.html */
	private String service;
	/** Specifies the name of the request parameter on where to find the service (i.e. service). */
	private String serviceParameterName = "service";
	/**
	 * The tolerance for drifting clocks when validating SAML tickets. 
	 * Note that 10 seconds should be more than enough for most environments that have NTP time synchronization. 
	 * Defaults to 1000 msec
	 */
	private long tolerance = 1000L;
	
	/* ================================== Shiro Pac4j Cas ================================= */
	
	/** Whether Enable Pac4j Cas. */
	private boolean enabled = false;
	
	/** The protocol of the CAS Client. */
	private CasProtocol casProtocol = CasProtocol.CAS20_PROXY;
	
    /** CasClient */
	private boolean casClient = false;
    private String casClientName = "cas";
    
    /** DirectCasClient */
    
    private boolean directCasClient = false;
    private String directCasClientName = "direct-cas";
    
    /** DirectCasProxyClient */
    
    private boolean directCasProxyClient = false;
    private String directCasProxyClientName = "direct-cas-proxy";
    
    /** CasRestBasicAuthClient */
    
    private boolean casRestBasicAuthClient = false;
    private String casRestBasicAuthClientName = "cas-rest-basic";
    private String headerName = HttpConstants.AUTHORIZATION_HEADER;
    private String prefixHeader = HttpConstants.BASIC_HEADER_PREFIX;
    
    /** CasRestFormClient */
    
    private boolean casRestFormClient = false;
    private String casRestFormClientName = "cas-rest-form";
    private String usernameParameterName = Pac4jConstants.USERNAME;
    private String passwordParameterName = Pac4jConstants.PASSWORD;

	public String getCasServerLoginUrl() {
		return casServerLoginUrl;
	}

	public void setCasServerLoginUrl(String casServerLoginUrl) {
		this.casServerLoginUrl = casServerLoginUrl;
	}

	public String getCasServerLogoutUrl() {
		return casServerLogoutUrl;
	}

	public void setCasServerLogoutUrl(String casServerLogoutUrl) {
		this.casServerLogoutUrl = casServerLogoutUrl;
	}

	public String getCasServerRestUrl() {
		return casServerRestUrl;
	}

	public void setCasServerRestUrl(String casServerRestUrl) {
		this.casServerRestUrl = casServerRestUrl;
	}

	public String getCasServerUrlPrefix() {
		return casServerUrlPrefix;
	}

	public void setCasServerUrlPrefix(String casServerUrlPrefix) {
		this.casServerUrlPrefix = casServerUrlPrefix;
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public boolean isEncodeServiceUrl() {
		return encodeServiceUrl;
	}

	public void setEncodeServiceUrl(boolean encodeServiceUrl) {
		this.encodeServiceUrl = encodeServiceUrl;
	}

	public boolean isGateway() {
		return gateway;
	}

	public void setGateway(boolean gateway) {
		this.gateway = gateway;
	}

	public boolean isRenew() {
		return renew;
	}

	public void setRenew(boolean renew) {
		this.renew = renew;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	/*public String getServerCallbackUrl() {
		return serverCallbackUrl;
	}

	public void setServerCallbackUrl(String serverCallbackUrl) {
		this.serverCallbackUrl = serverCallbackUrl;
	}*/

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getServiceParameterName() {
		return serviceParameterName;
	}

	public void setServiceParameterName(String serviceParameterName) {
		this.serviceParameterName = serviceParameterName;
	}

	public long getTolerance() {
		return tolerance;
	}

	public void setTolerance(long tolerance) {
		this.tolerance = tolerance;
	}

	public CasProtocol getCasProtocol() {
		return casProtocol;
	}

	public void setCasProtocol(CasProtocol casProtocol) {
		this.casProtocol = casProtocol;
	}

	public boolean isCasClient() {
		return casClient;
	}

	public void setCasClient(boolean casClient) {
		this.casClient = casClient;
	}

	public String getCasClientName() {
		return casClientName;
	}

	public void setCasClientName(String casClientName) {
		this.casClientName = casClientName;
	}

	public boolean isDirectCasClient() {
		return directCasClient;
	}

	public void setDirectCasClient(boolean directCasClient) {
		this.directCasClient = directCasClient;
	}

	public String getDirectCasClientName() {
		return directCasClientName;
	}

	public void setDirectCasClientName(String directCasClientName) {
		this.directCasClientName = directCasClientName;
	}

	public boolean isDirectCasProxyClient() {
		return directCasProxyClient;
	}

	public void setDirectCasProxyClient(boolean directCasProxyClient) {
		this.directCasProxyClient = directCasProxyClient;
	}

	public String getDirectCasProxyClientName() {
		return directCasProxyClientName;
	}

	public void setDirectCasProxyClientName(String directCasProxyClientName) {
		this.directCasProxyClientName = directCasProxyClientName;
	}

	public boolean isCasRestBasicAuthClient() {
		return casRestBasicAuthClient;
	}

	public void setCasRestBasicAuthClient(boolean casRestBasicAuthClient) {
		this.casRestBasicAuthClient = casRestBasicAuthClient;
	}

	public String getCasRestBasicAuthClientName() {
		return casRestBasicAuthClientName;
	}

	public void setCasRestBasicAuthClientName(String casRestBasicAuthClientName) {
		this.casRestBasicAuthClientName = casRestBasicAuthClientName;
	}

	public String getHeaderName() {
		return headerName;
	}

	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}

	public String getPrefixHeader() {
		return prefixHeader;
	}

	public void setPrefixHeader(String prefixHeader) {
		this.prefixHeader = prefixHeader;
	}

	public boolean isCasRestFormClient() {
		return casRestFormClient;
	}

	public void setCasRestFormClient(boolean casRestFormClient) {
		this.casRestFormClient = casRestFormClient;
	}

	public String getCasRestFormClientName() {
		return casRestFormClientName;
	}

	public void setCasRestFormClientName(String casRestFormClientName) {
		this.casRestFormClientName = casRestFormClientName;
	}

	public String getUsernameParameterName() {
		return usernameParameterName;
	}

	public void setUsernameParameterName(String usernameParameterName) {
		this.usernameParameterName = usernameParameterName;
	}

	public String getPasswordParameterName() {
		return passwordParameterName;
	}

	public void setPasswordParameterName(String passwordParameterName) {
		this.passwordParameterName = passwordParameterName;
	}
     
	
	
}
