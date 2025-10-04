package com.example.studentsapi.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.studentsapi.model.User;
import com.example.studentsapi.repository.UserRepo;
@Service
public class ApplicationUserService implements UserDetailsService{

    private final UserRepo userRepository;

    public ApplicationUserService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));

        return new ApplicationUser(
                userEntity.getRole().getGrantedAuthorities(),
                userEntity.getPassword(),
                userEntity.getUsername(),
                userEntity.isAccountNonExpired(),
                userEntity.isAccountNonLocked(),
                userEntity.isCredentialsNonExpired(),
                userEntity.isEnabled()
        );
    }


}
