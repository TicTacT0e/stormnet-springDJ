package app.config.beans;

import app.services.OAuth2Service;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:oauth/oAuth.properties")
public class OAuthConfig {

    @Bean
    public OAuth2Service getOAuth2Service() {
        return new OAuth2Service();
    }
}
