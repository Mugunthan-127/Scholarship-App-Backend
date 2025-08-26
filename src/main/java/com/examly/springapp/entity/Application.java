package com.examly.springapp.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "applications")
public class Application {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
   
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "scholarship_id", nullable = false)
    private Scholarship scholarship;
    
    @Column(nullable = false)
    private LocalDate applicationDate;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStatus status;
    
    @Column(length = 500)
    private String documents;
    
    @Column(length = 1000)
    private String comments;
    @ManyToOne
@JoinColumn(name = "student_id")
@JsonBackReference
private Student student;
    
    // Default constructor
    public Application() {
        this.applicationDate = LocalDate.now();
        this.status = ApplicationStatus.PENDING;
    }
    
    // Constructor
    public Application(Student student, Scholarship scholarship, String documents) {
        this();
        this.student = student;
        this.scholarship = scholarship;
        this.documents = documents;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Student getStudent() {
        return student;
    }
    
    public void setStudent(Student student) {
        this.student = student;
    }
    
    public Scholarship getScholarship() {
        return scholarship;
    }
    
    public void setScholarship(Scholarship scholarship) {
        this.scholarship = scholarship;
    }
    
    public LocalDate getApplicationDate() {
        return applicationDate;
    }
    
    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }
    
    public ApplicationStatus getStatus() {
        return status;
    }
    
    public void setStatus(ApplicationStatus status) {
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