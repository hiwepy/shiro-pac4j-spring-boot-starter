package org.apache.shiro.spring.boot.pac4j;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.pac4j.spring.boot.Pac4jLogoutProperties;
import org.pac4j.spring.boot.Pac4jProperties;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for Pac4j properties classes.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@DisplayName("Pac4j Properties Tests")
class Pac4jPropertiesTest {

    @Test
    @DisplayName("Pac4jProperties can be created with defaults")
    void testPac4jPropertiesDefaults() {
        Pac4jProperties props = new Pac4jProperties();
        assertThat(props).isNotNull();
        assertThat(props.getClients()).isNull();
        assertThat(props.getAuthorizers()).isNull();
        assertThat(props.getMatchers()).isNull();
        assertThat(props.getLoginUrl()).isEqualTo("/login");
    }

    @Test
    @DisplayName("Pac4jProperties setters work correctly")
    void testPac4jPropertiesSetters() {
        Pac4jProperties props = new Pac4jProperties();
        props.setClients("testClient");
        props.setAuthorizers("testAuth");
        props.setMatchers("testMatcher");
        props.setLoginUrl("/custom-login");

        assertThat(props.getClients()).isEqualTo("testClient");
        assertThat(props.getAuthorizers()).isEqualTo("testAuth");
        assertThat(props.getMatchers()).isEqualTo("testMatcher");
        assertThat(props.getLoginUrl()).isEqualTo("/custom-login");
    }

    @Test
    @DisplayName("Pac4jLogoutProperties can be created with defaults")
    void testPac4jLogoutPropertiesDefaults() {
        Pac4jLogoutProperties props = new Pac4jLogoutProperties();
        assertThat(props).isNotNull();
        assertThat(props.isCentralLogout()).isTrue();
        assertThat(props.isLocalLogout()).isTrue();
        assertThat(props.getDefaultUrl()).isEqualTo("/");
        assertThat(props.getPathPattern()).isNull();
    }

    @Test
    @DisplayName("Pac4jLogoutProperties setters work correctly")
    void testPac4jLogoutPropertiesSetters() {
        Pac4jLogoutProperties props = new Pac4jLogoutProperties();
        props.setCentralLogout(false);
        props.setLocalLogout(false);
        props.setDefaultUrl("/custom-logout");
        props.setPathPattern("/logout/**");

        assertThat(props.isCentralLogout()).isFalse();
        assertThat(props.isLocalLogout()).isFalse();
        assertThat(props.getDefaultUrl()).isEqualTo("/custom-logout");
        assertThat(props.getPathPattern()).isEqualTo("/logout/**");
    }
}
