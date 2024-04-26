package com.harbe.gatewayservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import com.harbe.gatewayservice.exception.AccessDeniedExceptionHandler;
import com.harbe.gatewayservice.exception.AuthenticationExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuerUri;

    private final ObjectMapper objectMapper;
    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withIssuerLocation(issuerUri).build();
    }

    @Bean
    public AuthenticationExceptionHandler authenticationExceptionHandler() {
        return new AuthenticationExceptionHandler(objectMapper);
    }

    @Bean
    public AccessDeniedExceptionHandler accessDeniedExceptionHandler() {
        return new AccessDeniedExceptionHandler(objectMapper);
    }
    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(auth -> auth
                        .pathMatchers("/actuator/**").permitAll()
                        .pathMatchers(HttpMethod.POST, "api/oauth2/v1/token").permitAll()
                        .pathMatchers(HttpMethod.GET, "api/auth/signup").permitAll()
                        .pathMatchers(HttpMethod.GET, "api/auth/register").permitAll()
                        .pathMatchers(HttpMethod.GET, "api/v1/products/**").permitAll()
                        .pathMatchers(HttpMethod.GET, "api/v1/categories/**").permitAll()

                        .pathMatchers(HttpMethod.GET, "api/users/**").hasAuthority("SCOPE_ADMIN")
                        .pathMatchers(HttpMethod.POST, "api/v1/products/**").hasAuthority("SCOPE_ADMIN")
                        .pathMatchers(HttpMethod.PUT, "api/v1/products/**").hasAuthority("SCOPE_ADMIN")
                        .pathMatchers(HttpMethod.DELETE, "api/v1/products/**").hasAuthority("SCOPE_ADMIN")
                        .anyExchange().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .authenticationEntryPoint(authenticationExceptionHandler())
                        .accessDeniedHandler(accessDeniedExceptionHandler())
                        .jwt(Customizer.withDefaults())
                )
                .build();
    }
}

