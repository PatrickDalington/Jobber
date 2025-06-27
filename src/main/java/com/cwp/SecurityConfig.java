package com.cwp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cwp.security.JwtFilter;

@Configuration
public class SecurityConfig {

	@Autowired
	private JwtFilter jwtFilter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       return http
    		   .csrf(csrf -> csrf.disable())
    		   .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    		   .authorizeHttpRequests(auth -> auth
    				   .requestMatchers(HttpMethod.POST, "/api/users", "/api/auth/login").permitAll()
    				   .requestMatchers(HttpMethod.GET, "/api/jobs/**").permitAll()
    				   .requestMatchers(HttpMethod.POST, "/api/jobs").hasAuthority("EMPLOYER")
    				   .anyRequest().authenticated()

    		).addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
    		   .build();
    }
}

