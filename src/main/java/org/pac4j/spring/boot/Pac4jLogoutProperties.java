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

    public boolean isCentralLogout() {
        return centralLogout;
    }

    public void setCentralLogout(boolean centralLogout) {
        this.centralLogout = centralLogout;
    }

    public boolean isLocalLogout() {
        return localLogout;
    }

    public void setLocalLogout(boolean localLogout) {
        this.localLogout = localLogout;
    }

    public String getDefaultUrl() {
        return defaultUrl;
    }

    public void setDefaultUrl(String defaultUrl) {
        this.defaultUrl = defaultUrl;
    }

    public String getPathPattern() {
        return pathPattern;
    }

    public void setPathPattern(String pathPattern) {
        this.pathPattern = pathPattern;
    }
}
