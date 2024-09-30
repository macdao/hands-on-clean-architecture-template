package com.example.demo.adapter.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;
import org.springframework.security.web.context.NullSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .securityContext(securityContext ->
                        securityContext.securityContextRepository(new RequestAttributeSecurityContextRepository()))
                .authenticationProvider(preAuthenticatedAuthenticationProvider())
                .with(new RequestHeaderAuthenticationFilterConfigurer(), Customizer.withDefaults())
                .build();
    }

    @Bean
    public PreAuthenticatedAuthenticationProvider preAuthenticatedAuthenticationProvider() {
        PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
        provider.setPreAuthenticatedUserDetailsService(token ->
                User.withUsername((String) token.getPrincipal()).password("N/A").build());
        return provider;
    }

    private static class RequestHeaderAuthenticationFilterConfigurer
            extends AbstractHttpConfigurer<RequestHeaderAuthenticationFilterConfigurer, HttpSecurity> {
        @Override
        public void configure(HttpSecurity http) throws Exception {
            RequestHeaderAuthenticationFilter filter = new RequestHeaderAuthenticationFilter();
            AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
            filter.setAuthenticationManager(authenticationManager);
            filter.setPrincipalRequestHeader("Authorization");
            filter.setExceptionIfHeaderMissing(false);
            filter.setSecurityContextRepository(new NullSecurityContextRepository());
            http.addFilter(filter);
        }
    }
}
