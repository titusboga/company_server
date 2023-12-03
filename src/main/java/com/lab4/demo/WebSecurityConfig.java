package com.lab4.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // If you're using CSRF tokens, consider whether you want to disable this.
                .authorizeRequests(authz -> authz
                        .anyRequest().permitAll() // This will allow any request without authentication.
                )
                .httpBasic().disable(); // Disable HTTP Basic authentication.

        return http.build();
    }
}
