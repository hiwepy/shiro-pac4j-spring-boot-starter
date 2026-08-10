package org.apache.shiro.spring.boot;

import org.apache.shiro.spring.boot.pac4j.ShiroPac4jFilterFactoryBean;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for shiro-pac4j-spring-boot-starter.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@DisplayName("Shiro Pac4j Starter Tests")
class ShiroPac4jStarterTest {

    @Test
    @DisplayName("ShiroPac4jProperties can be created")
    void testShiroPac4jProperties() {
        ShiroPac4jProperties props = new ShiroPac4jProperties();
        assertThat(props).isNotNull();
        assertThat(ShiroPac4jProperties.PREFIX).isEqualTo("shiro.pac4j");
    }

    @Test
    @DisplayName("ShiroPac4jProperties enabled flag defaults to false")
    void testShiroPac4jPropertiesDefaults() {
        ShiroPac4jProperties props = new ShiroPac4jProperties();
        assertThat(props.isEnabled()).isFalse();
    }

    @Test
    @DisplayName("ShiroPac4jProperties enabled flag can be set")
    void testShiroPac4jPropertiesSetEnabled() {
        ShiroPac4jProperties props = new ShiroPac4jProperties();
        props.setEnabled(true);
        assertThat(props.isEnabled()).isTrue();
    }

    @Test
    @DisplayName("ShiroPac4jWebAutoConfiguration can be instantiated")
    void testShiroPac4jWebAutoConfiguration() {
        ShiroPac4jWebAutoConfiguration config = new ShiroPac4jWebAutoConfiguration();
        assertThat(config).isNotNull();
    }

}
