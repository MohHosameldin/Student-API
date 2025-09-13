package com.example.studentsapi.Student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import   org.springframework.transaction.annotation.Transactional;


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
public Student findbyemail(String email) {
        return studentRepo.findStudentByEmail(email).orElseThrow(()-> new IllegalStateException("Student with email "+email+" does not exist"));
    }
    public Student findbynationalid(String NationalID) {
        return studentRepo.findStudentByNationalID(NationalID).orElseThrow(()-> new IllegalStateException("Student with National ID "+NationalID+" does not exist"));
    }


    public void addNewStudent(Student student) {
         if (student == null) {
        throw new IllegalArgumentException("Student cannot be null");
    }
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
    public void deleteStudent(Long id) {
        if(!studentRepo.existsById(id)){
            throw new IllegalStateException("Student with id "+id+" does not exist");
        }
        studentRepo.deleteById(id);
    }
    
    @Transactional
    public void updateStudent(Long id, String FirstName, String LastName, String email, String Address, String PhoneNumber) {
        Student student = studentRepo.findById(id).orElseThrow(()-> new IllegalStateException("Student with id "+id+" does not exist"));
        if(FirstName != null && FirstName.length()>0 && !FirstName.equals(student.getFirstName())){
            student.setFirstName(FirstName);
        }
        if(LastName != null && LastName.length()>0 && !LastName.equals(student.getLastName())){
            student.setLastName(LastName);
        }
        if(email != null && email.length()>0 && !email.equals(student.getEmail())){
            if(studentRepo.findStudentByEmail(email).isPresent()){
                throw new IllegalStateException("Email already taken");
            }
            student.setEmail(email);
        }
        if(Address != null && Address.length()>0 && !Address.equals(student.getAddress())){
            student.setAddress(Address);
        }
        if(PhoneNumber != null && PhoneNumber.length()>0 && !PhoneNumber.equals(student.getPhoneNumber())){
            if(studentRepo.findStudentByPhoneNumber(PhoneNumber).isPresent()){
                throw new IllegalStateException("Phone number already taken");
            }
            student.setPhoneNumber(PhoneNumber);
        }
    }
public void deleteStudent (long id){
    if(studentRepo.findStudentById(id).isPresent()){
        studentRepo.deleteById(id);
    } else {
        throw new IllegalStateException("Student with id "+id+" does not exist");
    }
}



}
