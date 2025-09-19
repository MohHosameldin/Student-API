package com.example.studentsapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class ApplicationSecurityConfig {

  

    @Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            // RULE 1 (Most Specific): Secure your API.
            // Any URL starting with /api/ requires the user to have the STUDENT role.
            .requestMatchers("/api/**").hasRole(ApplicationUserRole.STUDENT.name())

            // RULE 2: Make static content and the homepage public.
            .requestMatchers("/", "/index.html", "/css/**", "/js/**").permitAll()

            // RULE 3 (Least Specific): Any other request not matched above must be authenticated.
            .anyRequest().authenticated()
        )
        .httpBasic(Customizer.withDefaults());
    return http.build();
}

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails adminUser = User.builder()
                .username("linda")
                .password(passwordEncoder.encode("password123"))
                .roles(ApplicationUserRole.ADMIN.name())
                .build();

        UserDetails studentUser = User.builder()
                .username("annasmith")
                .password(passwordEncoder.encode("password123"))
                .roles(ApplicationUserRole.STUDENT.name())
                .build();
                UserDetails Tom = User.builder()
                .username("Tom")
                .password(passwordEncoder.encode("password123"))
                .roles(ApplicationUserRole.STUDENT.name())
                .build();
        
        return new InMemoryUserDetailsManager(adminUser, studentUser,Tom);
    }
}