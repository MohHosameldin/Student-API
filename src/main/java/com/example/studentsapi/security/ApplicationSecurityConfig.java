package com.example.studentsapi.security;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity 
public class ApplicationSecurityConfig {

  
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf
            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        )
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/", "/index.html", "/css/**", "/js/**").permitAll()
            
            .requestMatchers(HttpMethod.POST, "/api/**").hasAuthority(ApplicationUserPermission.STUDENT_WRITE.getPermission())
            .requestMatchers(HttpMethod.PUT, "/api/**").hasAuthority(ApplicationUserPermission.STUDENT_WRITE.getPermission())
            .requestMatchers(HttpMethod.DELETE, "/api/**").hasAuthority(ApplicationUserPermission.STUDENT_WRITE.getPermission())
            .requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole(ApplicationUserRole.ADMIN.name(), ApplicationUserRole.ADMINTRAINEE.name(), ApplicationUserRole.STUDENT.name())
            .anyRequest().authenticated()
        )
        .formLogin(form -> form
            .loginPage("/login") 
            .defaultSuccessUrl("/dashboard.html", true) 
            .permitAll() 
        )
            .rememberMe(rememberMe -> rememberMe
            .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21)) 
            .key("somethingverysecured") 
            .rememberMeParameter("remember-me") 
        )
        .logout(logout -> logout
            .logoutSuccessUrl("/")
            .invalidateHttpSession(true)
            .clearAuthentication(true) 
            .deleteCookies("JSESSIONID", "XSRF-TOKEN", "remember-me")           
        );
        
    return http.build();
}
    



    @Bean
    public CsrfTokenRepository csrfTokenRepository() {
        CookieCsrfTokenRepository repository = CookieCsrfTokenRepository.withHttpOnlyFalse();
        repository.setCookieName("XSRF-TOKEN");
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }

    
}