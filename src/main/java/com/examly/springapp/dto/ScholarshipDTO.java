// ScholarshipDTO.java
package com.examly.springapp.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class ScholarshipDTO {
    private Long id;
    
    @NotBlank(message = "Name is required")
    private String name;
    
    @NotBlank(message = "Description is required")
    private String description;
    
    @NotNull(message = "Amount is required")
    @Min(value = 1, message = "Amount should be positive")
    private Integer amount;
    
    @NotNull(message = "Deadline is required")
    @Future(message = "Deadline should be in the future")
    private LocalDate deadline;
    
    @NotBlank(message = "Criteria is required")
    private String criteria;
    
    private Boolean isActive = true;

    // Constructors, getters and setters
    
   

    public ScholarshipDTO() {
        this.id = id;
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.deadline = deadline;
        this.criteria = criteria;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getAmount() {
        return amount;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public String getCriteria() {
        return criteria;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    // Add all getters and setters here
    // ...
}