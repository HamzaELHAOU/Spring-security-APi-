package com.binga.binga.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class ConfigSecurity  {
    private final JwtAuthanticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    public ConfigSecurity(JwtAuthanticationFilter jwtAuthFilter, AuthenticationProvider authenticationProvider) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws  Exception{
     return  httpSecurity
        .csrf(csrf->csrf.disable())
        .authorizeRequests(auth->auth.requestMatchers("/api/v1/auth/***").permitAll().anyRequest().authenticated())
        .sessionManagement(sesm->sesm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        .httpBasic(Customizer.withDefaults()).build();


 /*  .authorizeRequests(auth->auth.anyRequest().authenticated())
            chaque fonction besion une authantifivation

             .sessionManagement(sesm->sesm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
             dit au serveur que nous allons utiliser jwt

requestMatchers("/api/v1/auth/**") : donner une authorization a cet api  "/api/v1/auth/**"
            */


    }
}
