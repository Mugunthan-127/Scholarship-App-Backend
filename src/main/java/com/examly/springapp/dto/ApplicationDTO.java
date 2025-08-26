// ApplicationDTO.java
package com.examly.springapp.dto;

import java.time.LocalDate;

public class ApplicationDTO {
    private Long id;
    private Long studentId;
    private ScholarshipDTO scholarship;
    private LocalDate applicationDate;
        private StudentDTO student; 
    private ApplicationStatusDTO status;  // Use ApplicationStatusDTO here
    private String documents;
    private String comments;

    // Constructors, getters and setters
    public ApplicationDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public ScholarshipDTO getScholarship() {
        return scholarship;
    }

    public void setScholarship(ScholarshipDTO scholarship) {
        this.scholarship = scholarship;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    public ApplicationStatusDTO getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatusDTO status) {
        this.status = status;
    }

    public String getDocuments() {
        return documents;
    }

    public void setDocuments(String documents) {
        this.documents = documents;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}