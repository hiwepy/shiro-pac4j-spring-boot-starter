package org.apache.shiro.spring.boot.pac4j;

import org.apache.shiro.spring.boot.pac4j.ext.Pac4jAntPathMatcher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link Pac4jAntPathMatcher}.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@DisplayName("Pac4jAntPathMatcher Tests")
class Pac4jAntPathMatcherTest {

    @Test
    @DisplayName("Matcher can be instantiated")
    void testInstantiation() {
        Pac4jAntPathMatcher matcher = new Pac4jAntPathMatcher();
        assertThat(matcher).isNotNull();
    }

    @Test
    @DisplayName("Matcher extends PathMatcher")
    void testExtendsPathMatcher() {
        Pac4jAntPathMatcher matcher = new Pac4jAntPathMatcher();
        assertThat(matcher).isInstanceOf(org.pac4j.core.matching.matcher.PathMatcher.class);
    }
}
