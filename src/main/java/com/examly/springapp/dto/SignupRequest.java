package com.examly.springapp.dto;

import com.examly.springapp.entity.Role;
import jakarta.validation.constraints.*;

public class SignupRequest {
    
    @NotBlank(message = "Username is required")
    private String username;
    
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;
    
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password should be at least 6 characters")
    private String password;
    
    @NotNull(message = "Role is required")
    private Role role;
    
    @NotBlank(message = "Name is required")
    private String name;
    
    @Pattern(regexp = "\\d{10}", message = "Phone number should be 10 digits")
    private String phoneNumber;
    
    @NotBlank(message = "Department is required")
    private String department;
    
    // For students only
    @Min(value = 1, message = "Year of study should be at least 1")
    @Max(value = 4, message = "Year of study should not exceed 4")
    private Integer yearOfStudy;
    
    @DecimalMin(value = "0.0", message = "CGPA should be at least 0.0")
    @DecimalMax(value = "10.0", message = "CGPA should not exceed 10.0")
    private Double cgpa;

    // 🔑 Admin secret
    private String adminSecret;

    // Constructors
    public SignupRequest() {}

    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    
    public Integer getYearOfStudy() { return yearOfStudy; }
    public void setYearOfStudy(Integer yearOfStudy) { this.yearOfStudy = yearOfStudy; }
    
    public Double getCgpa() { return cgpa; }
    public void setCgpa(Double cgpa) { this.cgpa = cgpa; }
    
    public String getAdminSecret() { return adminSecret; }
    public void setAdminSecret(String adminSecret) { this.adminSecret = adminSecret; }
}
