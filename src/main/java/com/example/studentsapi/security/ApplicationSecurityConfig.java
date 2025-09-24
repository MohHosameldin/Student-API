package com.example.studentsapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.config.http.SessionCreationPolicy;
@Configuration
@EnableWebSecurity
@EnableMethodSecurity //this is for the @preauth
public class ApplicationSecurityConfig {

  
@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Enable CSRF and configure the cookie repository
            .csrf(csrf -> csrf
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            )
            // Ensure a session is created to store the token
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
            )
            // Add your custom filter to force the CSRF cookie to be sent on every request
            .addFilterAfter(new CsrfCookieFilter(), CsrfFilter.class)
            // Your authorization rules
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.POST, "/api/**").hasAuthority(ApplicationUserPermission.STUDENT_WRITE.getPermission())
                .requestMatchers(HttpMethod.PUT, "/api/**").hasAuthority(ApplicationUserPermission.STUDENT_WRITE.getPermission())
                .requestMatchers(HttpMethod.DELETE, "/api/**").hasAuthority(ApplicationUserPermission.STUDENT_WRITE.getPermission())
                .requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole(ApplicationUserRole.ADMIN.name(), ApplicationUserRole.ADMINTRAINEE.name(), ApplicationUserRole.STUDENT.name())
                .requestMatchers("/", "/index.html", "/css/**", "/js/**").permitAll()
                .anyRequest().authenticated()
            )
            // Use HTTP Basic Authentication
            .httpBasic(Customizer.withDefaults());
            
        return http.build();
        
    }
    
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails adminUser = User.builder()
                .username("linda")
                .password(passwordEncoder.encode("password123"))
                .authorities(ApplicationUserRole.ADMIN.getGrantedAuthorities())
                .build();

        UserDetails studentUser = User.builder()
                .username("annasmith")
                .password(passwordEncoder.encode("password123"))
                .authorities(ApplicationUserRole.STUDENT.getGrantedAuthorities())
                .build();
                UserDetails Tom = User.builder()
                .username("Tom")
                .password(passwordEncoder.encode("password123"))
                .authorities(ApplicationUserRole.ADMINTRAINEE.getGrantedAuthorities())
                .build();
        
        return new InMemoryUserDetailsManager(adminUser, studentUser,Tom);
    }


    @Bean
    public CsrfTokenRepository csrfTokenRepository() {
        CookieCsrfTokenRepository repository = CookieCsrfTokenRepository.withHttpOnlyFalse();
        repository.setCookieName("XSRF-TOKEN");
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }

    
}