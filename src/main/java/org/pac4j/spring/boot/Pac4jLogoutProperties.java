package org.pac4j.spring.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for Pac4j logout integration.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@ConfigurationProperties(Pac4jLogoutProperties.PREFIX)
public class Pac4jLogoutProperties {

    public static final String PREFIX = "pac4j.logout";

    private boolean centralLogout = true;
    private boolean localLogout = true;
    private String defaultUrl = "/";
    private String pathPattern;

    /**
     * Returns the central logout.
     *
     * @return the central logout
     */
    public boolean isCentralLogout() {
        return centralLogout;
    }

    /**
     * Sets the central logout.
     *
     * @param centralLogout the central logout
     */
    public void setCentralLogout(boolean centralLogout) {
        this.centralLogout = centralLogout;
    }

    /**
     * Returns the local logout.
     *
     * @return the local logout
     */
    public boolean isLocalLogout() {
        return localLogout;
    }

    /**
     * Sets the local logout.
     *
     * @param localLogout the local logout
     */
    public void setLocalLogout(boolean localLogout) {
        this.localLogout = localLogout;
    }

    /**
     * Returns the default url.
     *
     * @return the default url
     */
    public String getDefaultUrl() {
        return defaultUrl;
    }

    /**
     * Sets the default url.
     *
     * @param defaultUrl the default url
     */
    public void setDefaultUrl(String defaultUrl) {
        this.defaultUrl = defaultUrl;
    }

    /**
     * Returns the path pattern.
     *
     * @return the path pattern
     */
    public String getPathPattern() {
        return pathPattern;
    }

    /**
     * Sets the path pattern.
     *
     * @param pathPattern the path pattern
     */
    public void setPathPattern(String pathPattern) {
        this.pathPattern = pathPattern;
    }
}
