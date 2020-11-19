package io.github.devtiagom.dynamicsearch.config;

import io.github.devtiagom.dynamicsearch.security.JWTAuthenticationFilter;
import io.github.devtiagom.dynamicsearch.security.JWTAuthorizarionFilter;
import io.github.devtiagom.dynamicsearch.security.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final Environment environment;

    private final UserDetailsService userDetailsService;

    private final JWTUtils jwtUtils;

    private static final String[] PUBLIC_MATCHERS = { "/h2-console/**" };

    private static final String[] PUBLIC_MATCHERS_GET = { "/api/v1/companies/**" };

    private static final String[] PUBLIC_MATCHERS_POST = { "/api/v1/users" };

    @Autowired
    public SecurityConfig(
            Environment environment,
            UserDetailsService userDetailsService,
            JWTUtils jwtUtils) {
        this.environment = environment;
        this.userDetailsService = userDetailsService;
        this.jwtUtils = jwtUtils;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if (Arrays.asList(environment.getActiveProfiles()).contains("dev")) {
            http.headers().frameOptions().disable();
        }

        http.csrf().disable()
        .authorizeRequests()
                .antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
                .antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
                .antMatchers(PUBLIC_MATCHERS).permitAll()
                .anyRequest().authenticated().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtils))
                .addFilter(new JWTAuthorizarionFilter(authenticationManager(), jwtUtils, userDetailsService));
    }
}
