package com.example.studentsapi.Student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final StudentRepo studentRepo;
    @Autowired
    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }
    public List<Student> getStudents() {
        return studentRepo.findAll();
    }
    
    public Student findbyid(Long id) {
        return studentRepo.findStudentById(id).orElseThrow(()-> new IllegalStateException("Student with id "+id+" does not exist"));
    }
    public void addNewStudent(Student student) {
        if(studentRepo.findStudentByEmail(student.getEmail()).isPresent()){
            throw new IllegalStateException("Email already taken");
        }
        if(studentRepo.findStudentByPhoneNumber(student.getPhoneNumber()).isPresent()){
            throw new IllegalStateException("Phone number already taken");
        }
        if(studentRepo.findStudentByNationalID(student.getNationalID()).isPresent()){
            throw new IllegalStateException("National ID already taken");
        }
        studentRepo.save(student);
    }


}
