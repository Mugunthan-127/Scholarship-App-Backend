package com.examly.springapp.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String name;
    
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    @Column(nullable = false, unique = true)
    private String email;
    
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "\\d{10}", message = "Phone number should be 10 digits")
    @Column(nullable = false)
    private String phoneNumber;
    
    @NotBlank(message = "Department is required")
    @Column(nullable = false)
    private String department;
    
    @Min(value = 1, message = "Year of study should be at least 1")
    @Max(value = 4, message = "Year of study should not exceed 4")
    @Column(nullable = false)
    private Integer yearOfStudy;
    
    @DecimalMin(value = "0.0", message = "CGPA should be at least 0.0")
    @DecimalMax(value = "10.0", message = "CGPA should not exceed 10.0")
    @Column(nullable = false)
    private Double cgpa;
    
    
@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
@JsonManagedReference
private List<Application> applications;

    
    // Default constructor
    public Student() {}
    
    // Constructor
    public Student(String name, String email, String phoneNumber, String department, 
                   Integer yearOfStudy, Double cgpa) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.department = department;
        this.yearOfStudy = yearOfStudy;
        this.cgpa = cgpa;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public Integer getYearOfStudy() {
        return yearOfStudy;
    }
    
    public void setYearOfStudy(Integer yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }
    
    public Double getCgpa() {
        return cgpa;
    }
    
    public void setCgpa(Double cgpa) {
        this.cgpa = cgpa;
    }
    
    public List<Application> getApplications() {
        return applications;
    }
    
    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    @OneToOne
@JoinColumn(name = "user_id")
private User user;

public User getUser() { return user; }
public void setUser(User user) { this.user = user; }
}