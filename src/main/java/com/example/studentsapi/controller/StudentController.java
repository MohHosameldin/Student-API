package com.example.studentsapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentsapi.model.Student;
import com.example.studentsapi.service.StudentService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping(path = "api/student")
@CrossOrigin(origins = "*")
public class StudentController {
private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping
    public List <Student> getStudents(){
        return studentService.getStudents();
    }
    @GetMapping(path = "{id}")
    public Student findbyid(@PathVariable("id") Long id) {
        return studentService.findbyid(id);
    }
    @GetMapping(path = "email/{email}")
    public Student findbyemail(@PathVariable("email") String email) {
        return studentService.findbyemail(email);
    }
@GetMapping("nationalid/{NationalID}")
public Student nationalIdSearch(@PathVariable("NationalID") String NationalID) {
    return studentService.findbynationalid(NationalID); 
}
@PostMapping
public void registerStudent(@Valid @RequestBody Student student){
studentService.addNewStudent(student);

}
@DeleteMapping(path="{id}") 
public void deleteStudent(@PathVariable("id") Long id){
    studentService.deleteStudent(id);
}

@PutMapping(path="{id}") 
public void updateStudent(@PathVariable("id")Long id ,
 @RequestParam(required = false) String firstName ,
  @RequestParam(required = false) String secondName , 
  @RequestParam(required = false) String email , 
  @RequestParam(required = false) String Address ,
  @RequestParam(required = false) String PhoneNumber){
studentService.updateStudent(id, firstName, secondName, email, Address, PhoneNumber);
}

}
