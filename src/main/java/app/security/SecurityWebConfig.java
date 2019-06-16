package app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@EnableWebSecurity
public class SecurityWebConfig extends WebSecurityConfigurerAdapter {

//    spring.security.oauth2.client.registration.google.client-id=1051488859825-rg4tekeudmntgl2ajshhin99k9a3qnnr.apps.googleusercontent.com
//    spring.security.oauth2.client.registration.google.client-secret=d7FO1N9hVFjw8TNP9mB2slll
//
//    spring.security.oauth2.client.registration.facebook.client-id=2369188316686862
//    spring.security.oauth2.client.registration.facebook.client-secret=e0532b19acd5dc9ef264ea95f129dca8


    private static List<String> clients = Arrays.asList("google", "facebook");

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        List<ClientRegistration> registrations = clients.stream()
                .map(c -> getRegistration(c))
                .filter(registration -> registration != null)
                .collect(Collectors.toList());

        return new InMemoryClientRegistrationRepository(registrations);
    }

//    private static String CLIENT_PROPERTY_KEY
//            = "spring.security.oauth2.client.registration.";
//
//    @Autowired
//    private Environment env;

    private ClientRegistration getRegistration(String client) {
//        String clientId = env.getProperty(
//                CLIENT_PROPERTY_KEY + client + ".client-id");
//
//        if (clientId == null) {
//            return null;
//        }
//
//        String clientSecret = env.getProperty(
//                CLIENT_PROPERTY_KEY + client + ".client-secret");

        if (client.equals("google")) {
            return CommonOAuth2Provider.GOOGLE.getBuilder(client)
                    .clientId("051488859825-rg4tekeudmntgl2ajshhin99k9a3qnnr.apps.googleusercontent.com")
                    .clientSecret("d7FO1N9hVFjw8TNP9mB2slll").build();
        }
        if (client.equals("facebook")) {
            return CommonOAuth2Provider.FACEBOOK.getBuilder(client)
                    .clientId("2369188316686862")
                    .clientSecret("e0532b19acd5dc9ef264ea95f129dca8").build();
        }
        return null;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/oauth_login").permitAll()
                .antMatchers("/users/*").hasAuthority("ADMIN")
                .antMatchers("/hotels/*").hasAuthority("ADMIN")
                .antMatchers("/rooms/*").permitAll()
                .antMatchers("/orders/*").authenticated()
                .antMatchers("/*").rememberMe().anyRequest()
                .authenticated().and()
                .oauth2Login().loginPage("/oauth_login");
    }

    @Bean
    public OAuth2AuthorizedClientService authorizedClientService() {

        return new InMemoryOAuth2AuthorizedClientService(
                clientRegistrationRepository());
    }
}
