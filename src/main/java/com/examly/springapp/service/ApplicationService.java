package com.examly.springapp.service;

import com.examly.springapp.entity.Application;
import com.examly.springapp.entity.ApplicationStatus;
import com.examly.springapp.entity.Student;
import com.examly.springapp.entity.Scholarship;
import com.examly.springapp.repository.ApplicationRepository;
import com.examly.springapp.repository.StudentRepository;
import com.examly.springapp.repository.ScholarshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ScholarshipRepository scholarshipRepository;

    public Application submitApplication(Long studentId, Long scholarshipId, String documents) {
        // Check if application already exists
        if (applicationRepository.existsByStudentIdAndScholarshipId(studentId, scholarshipId)) {
            throw new RuntimeException("Application already exists for this student and scholarship");
        }

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));

        Scholarship scholarship = scholarshipRepository.findById(scholarshipId)
                .orElseThrow(() -> new RuntimeException("Scholarship not found with id: " + scholarshipId));

        if (!scholarship.getIsActive()) {
            throw new RuntimeException("Cannot apply to inactive scholarship");
        }

        Application application = new Application(student, scholarship, documents);
        return applicationRepository.save(application);
    }

    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    public List<Application> getApplicationsByStatus(ApplicationStatus status) {
        return applicationRepository.findByStatus(status);
    }

    public List<Application> getApplicationsByStudentId(Long studentId) {
        return applicationRepository.findByStudentId(studentId);
    }

    public Optional<Application> getApplicationById(Long id) {
        return applicationRepository.findById(id);
    }

    public Application updateApplicationStatus(Long id, ApplicationStatus status, String comments) {
        return applicationRepository.findById(id)
                .map(application -> {
                    application.setStatus(status);
                    if (comments != null) {
                        application.setComments(comments);
                    }
                    return applicationRepository.save(application);
                })
                .orElseThrow(() -> new RuntimeException("Application not found with id: " + id));
    }

    public void deleteApplication(Long id) {
        if (!applicationRepository.existsById(id)) {
            throw new RuntimeException("Application not found with id: " + id);
        }
        applicationRepository.deleteById(id);
    }

    // ✅ NEW helper method to support role-based checks
    public Long getStudentIdByEmail(String email) {
        return studentRepository.findByEmail(email)
                .map(Student::getId)
                .orElseThrow(() -> new RuntimeException("Student not found with email: " + email));
    }
}
