package com.example.studentsapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig {

  

    @Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
       .csrf(csrf -> csrf
            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        )
        .authorizeHttpRequests(auth -> auth
        .requestMatchers(HttpMethod.DELETE, "/api/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())
        .requestMatchers(HttpMethod.POST,"/api/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())
        .requestMatchers(HttpMethod.GET,"/api/**").hasAnyRole(ApplicationUserRole.ADMIN.name(), ApplicationUserRole.ADMINTRAINEE.name())


                .requestMatchers(HttpMethod.PUT,"/api/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.name())

            .requestMatchers("/api/**").hasRole(ApplicationUserRole.STUDENT.name())

            .requestMatchers("/", "/index.html", "/css/**", "/js/**").permitAll()

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