package com.examly.springapp.controller;

import com.examly.springapp.dto.ApplicationDTO;
import com.examly.springapp.entity.Application;
import com.examly.springapp.entity.ApplicationStatus;
import com.examly.springapp.mapper.StudentMapper;
import com.examly.springapp.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "http://localhost:3000")
public class ApplicationController {
    
    private final ApplicationService applicationService;
    private final StudentMapper studentMapper;
    
    @Autowired
    public ApplicationController(ApplicationService applicationService, StudentMapper studentMapper) {
        this.applicationService = applicationService;
        this.studentMapper = studentMapper;
    }
    
    @PostMapping
    public ResponseEntity<ApplicationDTO> submitApplication(@RequestBody Map<String, Object> request) {
        try {
            Long studentId = Long.valueOf(request.get("studentId").toString());
            Long scholarshipId = Long.valueOf(request.get("scholarshipId").toString());
            String documents = request.get("documents") != null ? request.get("documents").toString() : "";
            
            Application application = applicationService.submitApplication(studentId, scholarshipId, documents);
            return new ResponseEntity<>(studentMapper.toApplicationDto(application), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping
    public ResponseEntity<List<ApplicationDTO>> getApplications(@RequestParam(required = false) String status) {
        List<Application> applications;
        if (status != null) {
            try {
                ApplicationStatus appStatus = ApplicationStatus.valueOf(status.toUpperCase());
                applications = applicationService.getApplicationsByStatus(appStatus);
            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            applications = applicationService.getAllApplications();
        }
        return new ResponseEntity<>(studentMapper.toApplicationDtoList(applications), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApplicationDTO> getApplicationById(@PathVariable Long id) {
        return applicationService.getApplicationById(id)
                .map(application -> new ResponseEntity<>(studentMapper.toApplicationDto(application), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<ApplicationDTO>> getApplicationsByStudentId(@PathVariable Long studentId) {
        List<Application> applications = applicationService.getApplicationsByStudentId(studentId);
        return new ResponseEntity<>(studentMapper.toApplicationDtoList(applications), HttpStatus.OK);
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<ApplicationDTO> updateApplicationStatus(
            @PathVariable Long id, 
            @RequestBody Map<String, String> request) {
        try {
            String statusStr = request.get("status");
            String comments = request.get("comments");
            
            ApplicationStatus status = ApplicationStatus.valueOf(statusStr.toUpperCase());
            Application updatedApplication = applicationService.updateApplicationStatus(id, status, comments);
            return new ResponseEntity<>(studentMapper.toApplicationDto(updatedApplication), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {
        try {
            applicationService.deleteApplication(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}