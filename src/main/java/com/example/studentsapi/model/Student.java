package com.example.studentsapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1,
            initialValue = 250001
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;

    @NotBlank(message = "First name is mandatory")
    @Column(nullable = false)
    private String firstName; 

    @NotBlank(message = "Last name is mandatory")
    @Column(nullable = false)
    private String lastName; 

    @NotBlank(message = "Email is mandatory")
    @Column(nullable = false, unique = true)
    @Pattern(
            regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",
            message = "Invalid email address"
    )
    private String email;

    @NotBlank(message="Address is mandatory")
    @Column(nullable = false)
    private String address; 
    
@NotBlank(message="Phone number is mandatory")
@Pattern(
    regexp = "^01[0-9]{9}$",
    message = "Phone number must be 11 digits and start with 01"
)
@Column(nullable = false, unique = true)
private String phoneNumber;

    @NotNull(message="Date of Birth is mandatory")
    @Column(nullable = false)
    private LocalDate dateOfBirth; 

    @NotBlank(message="National ID is mandatory") 
    @Column(nullable = false, unique = true)
    @Pattern(
            regexp = "^[23][0-9]{13}$",
            message = "National ID must be 14 digits and start with 2 or 3"
    )
    private String nationalID; 

    @Transient
    private Integer age;

    public Student() {
    }

    public Student(
            String firstName,
            String lastName,
            String email,
            String address,
            String phoneNumber,
            LocalDate dateOfBirth,
            String nationalID
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.nationalID = nationalID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNationalID() {
        return nationalID;
    }

    public void setNationalID(String nationalID) {
        this.nationalID = nationalID;
    }

    public Integer getAge() {
        if (this.dateOfBirth == null) {
            return null;
        }
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", nationalID='" + nationalID + '\'' +
                '}';
    }
}