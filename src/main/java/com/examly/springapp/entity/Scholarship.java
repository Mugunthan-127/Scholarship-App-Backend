package com.examly.springapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "scholarships")
public class Scholarship {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String name;
    
    @NotBlank(message = "Description is required")
    @Column(nullable = false, length = 1000)
    private String description;
    
    @NotNull(message = "Amount is required")
    @Min(value = 1, message = "Amount should be positive")
    @Column(nullable = false)
    private Integer amount;
    
    @NotNull(message = "Deadline is required")
    @Future(message = "Deadline should be in the future")
    @Column(nullable = false)
    private LocalDate deadline;
    
    @NotBlank(message = "Criteria is required")
    @Column(nullable = false, length = 1000)
    private String criteria;
    
    @Column(nullable = false)
    private Boolean isActive = true;
    
    @OneToMany(mappedBy = "scholarship", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Application> applications;
    
    // Default constructor
    public Scholarship() {}
    
    // Constructor
    public Scholarship(String name, String description, Integer amount, 
                      LocalDate deadline, String criteria, Boolean isActive) {
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.deadline = deadline;
        this.criteria = criteria;
        this.isActive = isActive;
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
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Integer getAmount() {
        return amount;
    }
    
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    
    public LocalDate getDeadline() {
        return deadline;
    }
    
    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
    
    public String getCriteria() {
        return criteria;
    }
    
    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }
    
    public Boolean getIsActive() {
        return isActive;
    }
    
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    
    public List<Application> getApplications() {
        return applications;
    }
    
    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }
}