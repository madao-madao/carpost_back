package org.sark.carpost.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;

@Configuration
public class SecurityConfig {

    @Bean
    public JwtDecoder jwtDecoder() {
        String issuerUri = "http://localhost:8280/realms/carpostoriginal";
        return JwtDecoders.fromIssuerLocation(issuerUri);
    }
}