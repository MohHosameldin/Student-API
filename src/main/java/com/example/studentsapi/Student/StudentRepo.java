package com.example.studentsapi.Student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Long> {
Optional<Student> findStudentByEmail(String email);
Optional<Student> findStudentById(Long id);
Optional<Student> findStudentByPhoneNumber(String PhoneNumber);
Optional<Student> findStudentByNationalID(String NationalID);
}
