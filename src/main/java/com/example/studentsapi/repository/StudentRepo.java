package com.example.studentsapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.studentsapi.model.Student;
@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
Optional<Student> findStudentByEmail(String email);
Optional<Student> findStudentById(Long id);
Optional<Student> findStudentByPhoneNumber(String phoneNumber);
Optional<Student> findStudentByNationalID(String nationalID);
}
