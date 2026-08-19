package org.pac4j.spring.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for Pac4j integration.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@ConfigurationProperties(Pac4jProperties.PREFIX)
public class Pac4jProperties {

    public static final String PREFIX = "pac4j";

    private String clients;
    private String authorizers;
    private String matchers;
    private String loginUrl = "/login";

    /**
     * Returns the clients.
     *
     * @return the clients
     */
    public String getClients() {
        return clients;
    }

    /**
     * Sets the clients.
     *
     * @param clients the clients
     */
    public void setClients(String clients) {
        this.clients = clients;
    }

    /**
     * Returns the authorizers.
     *
     * @return the authorizers
     */
    public String getAuthorizers() {
        return authorizers;
    }

    /**
     * Sets the authorizers.
     *
     * @param authorizers the authorizers
     */
    public void setAuthorizers(String authorizers) {
        this.authorizers = authorizers;
    }

    /**
     * Returns the matchers.
     *
     * @return the matchers
     */
    public String getMatchers() {
        return matchers;
    }

    /**
     * Sets the matchers.
     *
     * @param matchers the matchers
     */
    public void setMatchers(String matchers) {
        this.matchers = matchers;
    }

    /**
     * Returns the login url.
     *
     * @return the login url
     */
    public String getLoginUrl() {
        return loginUrl;
    }

    /**
     * Sets the login url.
     *
     * @param loginUrl the login url
     */
    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }
}
