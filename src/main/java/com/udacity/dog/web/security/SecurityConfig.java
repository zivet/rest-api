package com.udacity.dog.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder auth) throws java.lang.Exception {
        auth.inMemoryAuthentication()
                .withUser("pep").password(passwordEncoder().encode("password")).roles("USER")
                .and().withUser("manager").password(passwordEncoder().encode("password")).roles("USER", "MANAGER");
    }

    @Override
    public void configure(org.springframework.security.config.annotation.web.builders.HttpSecurity http) throws java.lang.Exception {
        http.csrf().disable()
                .httpBasic()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests().anyRequest().authenticated();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
