package com.example.studentsapi.Student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(path = "api/student")
@CrossOrigin(origins = "*")
public class StudentController {
private final StudentService studentService;
@Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping
    public List <Student> getStudents(){
        return studentService.getStudents();
    }
    @GetMapping(path = "{id}")
    public Student findbyid(Long id) {
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
    


}
