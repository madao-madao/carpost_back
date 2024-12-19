package org.sark.carpost.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityKeycloakSandbox {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                 .csrf(AbstractHttpConfigurer::disable)
                 .cors(Customizer.withDefaults())
                 .authorizeHttpRequests(auth -> auth
                     .requestMatchers("/api/register", "/api/profile/edit").permitAll()
                     .anyRequest().authenticated()
                )
                    .oauth2Login(Customizer.withDefaults())
                    .oauth2ResourceServer(oauth2 -> oauth2
                    .jwt(Customizer.withDefaults())
            );

         return http.build();
    }
}


