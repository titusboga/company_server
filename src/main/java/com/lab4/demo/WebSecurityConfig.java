package com.lab4.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // If you're using CSRF tokens, consider whether you want to disable this.
                .authorizeRequests()
                .anyRequest().permitAll() // This will allow any request without authentication.
                .and()
                .httpBasic().disable(); // Disable HTTP Basic authentication.
    }
}
