package org.apache.shiro.spring.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>Configuration properties.</p>
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@ConfigurationProperties(ShiroPac4jProperties.PREFIX)
public class ShiroPac4jProperties {

	public static final String PREFIX = "shiro.pac4j";
	 
	/* ================================== Shiro Pac4j ================================= */
	
	/**
	 * Enable Shiro Pac4j.
	 */
	private boolean enabled = false;
    
	/**
	 * Returns the enabled.
	 *
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * Sets the enabled.
	 *
	 * @param enabled the enabled
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
