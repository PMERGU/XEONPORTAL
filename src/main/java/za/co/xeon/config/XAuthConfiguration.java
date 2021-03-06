package za.co.xeon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import za.co.xeon.security.xauth.TokenProvider;

/**
 * Configures x-auth-token security.
 */
@Configuration
public class XAuthConfiguration {

	@Bean
	public TokenProvider tokenProvider(JHipsterProperties jHipsterProperties) {
		String secret = jHipsterProperties.getSecurity().getAuthentication().getXauth().getSecret();
		int validityInSeconds = jHipsterProperties.getSecurity().getAuthentication().getXauth().getTokenValidityInSeconds();
		return new TokenProvider(secret, validityInSeconds);
	}
}
