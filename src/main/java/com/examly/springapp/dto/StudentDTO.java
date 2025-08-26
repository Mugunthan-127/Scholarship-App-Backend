// StudentDTO.java
package com.examly.springapp.dto;

import jakarta.validation.constraints.*;
import java.util.List;

public class StudentDTO {
    private Long id;
    
    @NotBlank(message = "Name is required")
    private String name;
    
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;
    
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "\\d{10}", message = "Phone number should be 10 digits")
    private String phoneNumber;
    
    @NotBlank(message = "Department is required")
    private String department;
    
    @Min(value = 1, message = "Year of study should be at least 1")
    @Max(value = 4, message = "Year of study should not exceed 4")
    private Integer yearOfStudy;
    
    @DecimalMin(value = "0.0", message = "CGPA should be at least 0.0")
    @DecimalMax(value = "10.0", message = "CGPA should not exceed 10.0")
    private Double cgpa;
    
    private List<ApplicationDTO> applications;

    public StudentDTO(Long id, @NotBlank(message = "Name is required") String name,
            @Email(message = "Email should be valid") @NotBlank(message = "Email is required") String email,
            @NotBlank(message = "Phone number is required") @Pattern(regexp = "\\d{10}", message = "Phone number should be 10 digits") String phoneNumber,
            @NotBlank(message = "Department is required") String department,
            @Min(value = 1, message = "Year of study should be at least 1") @Max(value = 4, message = "Year of study should not exceed 4") Integer yearOfStudy,
            @DecimalMin(value = "0.0", message = "CGPA should be at least 0.0") @DecimalMax(value = "10.0", message = "CGPA should not exceed 10.0") Double cgpa,
            List<ApplicationDTO> applications) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.department = department;
        this.yearOfStudy = yearOfStudy;
        this.cgpa = cgpa;
        this.applications = applications;
    }

    public StudentDTO() {
        //TODO Auto-generated constructor stub
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDepartment() {
        return department;
    }

    public Integer getYearOfStudy() {
        return yearOfStudy;
    }

    public Double getCgpa() {
        return cgpa;
    }

    public List<ApplicationDTO> getApplications() {
        return applications;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setYearOfStudy(Integer yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public void setCgpa(Double cgpa) {
        this.cgpa = cgpa;
    }

    public void setApplications(List<ApplicationDTO> applications) {
        this.applications = applications;
    }

    // Constructors, getters and setters
    
    
    // ...
}