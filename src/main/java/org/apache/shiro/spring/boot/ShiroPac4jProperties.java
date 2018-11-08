package org.apache.shiro.spring.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(ShiroPac4jProperties.PREFIX)
public class ShiroPac4jProperties {

	public static final String PREFIX = "shiro.pac4j";
	 
	/* ================================== Shiro Pac4j ================================= */
	
	/**
	 * Enable Shiro Pac4j.
	 */
	private boolean enabled = false;
    
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
