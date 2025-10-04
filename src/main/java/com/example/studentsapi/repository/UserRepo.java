package com.example.studentsapi.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import com.example.studentsapi.model.User;
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
 
    Optional<User> findByUsername(String username);
}
