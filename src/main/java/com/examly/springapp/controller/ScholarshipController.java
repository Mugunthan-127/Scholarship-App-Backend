package com.examly.springapp.controller;

import com.examly.springapp.dto.ScholarshipDTO;
import com.examly.springapp.entity.Scholarship;
import com.examly.springapp.mapper.StudentMapper;
import com.examly.springapp.service.ScholarshipService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/scholarships")
@CrossOrigin(origins = "https://scholarship-app-frontend.vercel.app")
public class ScholarshipController {
    
    private final ScholarshipService scholarshipService;
    private final StudentMapper studentMapper;
    
    @Autowired
    public ScholarshipController(ScholarshipService scholarshipService, StudentMapper studentMapper) {
        this.scholarshipService = scholarshipService;
        this.studentMapper = studentMapper;
    }
    
    @PostMapping
    public ResponseEntity<ScholarshipDTO> createScholarship(@Valid @RequestBody ScholarshipDTO scholarshipDTO) {
        Scholarship scholarship = studentMapper.toScholarshipEntity(scholarshipDTO);
        Scholarship createdScholarship = scholarshipService.createScholarship(scholarship);
        return new ResponseEntity<>(studentMapper.toScholarshipDto(createdScholarship), HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<ScholarshipDTO>> getScholarships(@RequestParam(required = false) Boolean active) {
        List<Scholarship> scholarships;
        if (active != null && active) {
            scholarships = scholarshipService.getActiveScholarships();
        } else {
            scholarships = scholarshipService.getAllScholarships();
        }
        return new ResponseEntity<>(studentMapper.toScholarshipDtoList(scholarships), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ScholarshipDTO> getScholarshipById(@PathVariable Long id) {
        return scholarshipService.getScholarshipById(id)
                .map(scholarship -> new ResponseEntity<>(studentMapper.toScholarshipDto(scholarship), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ScholarshipDTO> updateScholarship(@PathVariable Long id, @Valid @RequestBody ScholarshipDTO scholarshipDTO) {
        try {
            Scholarship scholarship = studentMapper.toScholarshipEntity(scholarshipDTO);
            Scholarship updatedScholarship = scholarshipService.updateScholarship(id, scholarship);
            return new ResponseEntity<>(studentMapper.toScholarshipDto(updatedScholarship), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}/toggle-status")
    public ResponseEntity<ScholarshipDTO> toggleScholarshipStatus(@PathVariable Long id) {
        try {
            Scholarship updatedScholarship = scholarshipService.toggleScholarshipStatus(id);
            return new ResponseEntity<>(studentMapper.toScholarshipDto(updatedScholarship), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search")
public ResponseEntity<List<Scholarship>> searchScholarships(
        @RequestParam(required = false) String keyword,
        @RequestParam(required = false) Boolean active) {
    
    List<Scholarship> scholarships = scholarshipService.searchScholarships(keyword, active);
    return ResponseEntity.ok(scholarships);
}

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScholarship(@PathVariable Long id) {
        try {
            scholarshipService.deleteScholarship(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}