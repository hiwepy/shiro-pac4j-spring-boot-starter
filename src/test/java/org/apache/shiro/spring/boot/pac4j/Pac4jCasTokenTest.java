package org.apache.shiro.spring.boot.pac4j;

import org.apache.shiro.biz.authc.token.LoginType;
import org.apache.shiro.spring.boot.pac4j.cas.token.Pac4jCasToken;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link Pac4jCasToken}.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@DisplayName("Pac4jCasToken Tests")
class Pac4jCasTokenTest {

    @Test
    @DisplayName("Token can be created with host and profiles")
    void testTokenCreation() {
        Pac4jCasToken token = new Pac4jCasToken("192.168.1.1", new ArrayList<>(), false);
        assertThat(token).isNotNull();
        assertThat(token.getHost()).isEqualTo("192.168.1.1");
    }

    @Test
    @DisplayName("Token principal returns username")
    void testGetPrincipal() {
        Pac4jCasToken token = new Pac4jCasToken("192.168.1.1", new ArrayList<>(), false);
        token.setUsername("testUser");
        assertThat(token.getPrincipal()).isEqualTo("testUser");
    }

    @Test
    @DisplayName("Token credentials returns ticket")
    void testGetCredentials() {
        Pac4jCasToken token = new Pac4jCasToken("192.168.1.1", new ArrayList<>(), false);
        token.setTicket("ST-123456");
        assertThat(token.getCredentials()).isEqualTo("ST-123456");
    }

    @Test
    @DisplayName("Token login type is CAS")
    void testGetLoginType() {
        Pac4jCasToken token = new Pac4jCasToken("192.168.1.1", new ArrayList<>(), false);
        assertThat(token.getLoginType()).isEqualTo(LoginType.CAS);
    }

    @Test
    @DisplayName("Token setters work correctly")
    void testSetters() {
        Pac4jCasToken token = new Pac4jCasToken("192.168.1.1", new ArrayList<>(), false);
        token.setHost("10.0.0.1");
        token.setUsername("newUser");
        token.setTicket("ST-654321");

        assertThat(token.getHost()).isEqualTo("10.0.0.1");
        assertThat(token.getPrincipal()).isEqualTo("newUser");
        assertThat(token.getCredentials()).isEqualTo("ST-654321");
    }
}
