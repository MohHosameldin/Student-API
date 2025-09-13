package com.example.studentsapi.Student;

import java.time.LocalDate;
import jakarta.persistence.Transient;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
private String FirstName;

@NotBlank(message = "Last name is mandatory")
@Column(nullable = false)
   private String LastName;

@NotBlank(message = "Email is mandatory")
@Column(nullable = false, unique = true)
@Pattern(
    regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",
    message = "Invalid email address"
)
private String email;

@NotBlank(message="Address is mandatory")
@Column(nullable = false)
   private String Address;

@Pattern(
        regexp = "^[0-9]{10,15}$",
        message = "Phone number must contain only digits (10–15 digits)"
    )
@NotBlank(message="Phone number is mandatory")
@Column(nullable = false, unique = true)
   private String PhoneNumber;

@NotNull(message="Date of Birth is mandatory")
@Column(nullable = false)
    private LocalDate DateOfBirth;

@NotBlank(message="Phone number is mandatory")
@Column(nullable = false, unique = true)
@Pattern(
    regexp = "^[23][0-9]{13}$",
    message = "National ID must be 14 digits and start with 2 or 3"
)
   private String NationalID;
   @Transient
   private Integer age;

  
    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        this.FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        this.LastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        this.Address = address;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.PhoneNumber = phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.DateOfBirth = dateOfBirth;
    }

    public Integer getAge() {
        return Period.between(this.DateOfBirth, LocalDate.now()).getYears();

    }
public String getNationalID() {
    return NationalID; 
}
public void setNationalID(String nationalID) {
    this.NationalID = nationalID;
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
        this.FirstName = firstName;
        this.LastName = lastName;
        this.email = email;
        this.Address = address;
        this.PhoneNumber = phoneNumber;
        this.DateOfBirth = dateOfBirth;
        this.NationalID = nationalID;
    }


}
