package com.examly.springapp.service;

import com.examly.springapp.entity.Scholarship;
import com.examly.springapp.repository.ScholarshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ScholarshipService {
    
    @Autowired
    private ScholarshipRepository scholarshipRepository;
    
    public Scholarship createScholarship(Scholarship scholarship) {
        return scholarshipRepository.save(scholarship);
    }
    
    public List<Scholarship> getAllScholarships() {
        return scholarshipRepository.findAll();
    }
    
    public List<Scholarship> getActiveScholarships() {
        return scholarshipRepository.findByIsActiveTrue();
    }
    
    public Optional<Scholarship> getScholarshipById(Long id) {
        return scholarshipRepository.findById(id);
    }
    
    public Scholarship updateScholarship(Long id, Scholarship updatedScholarship) {
        return scholarshipRepository.findById(id)
                .map(scholarship -> {
                    scholarship.setName(updatedScholarship.getName());
                    scholarship.setDescription(updatedScholarship.getDescription());
                    scholarship.setAmount(updatedScholarship.getAmount());
                    scholarship.setDeadline(updatedScholarship.getDeadline());
                    scholarship.setCriteria(updatedScholarship.getCriteria());
                    scholarship.setIsActive(updatedScholarship.getIsActive());
                    return scholarshipRepository.save(scholarship);
                })
                .orElseThrow(() -> new RuntimeException("Scholarship not found with id: " + id));
    }
    
    public void deleteScholarship(Long id) {
        if (!scholarshipRepository.existsById(id)) {
            throw new RuntimeException("Scholarship not found with id: " + id);
        }
        scholarshipRepository.deleteById(id);
    }
    
    public Scholarship toggleScholarshipStatus(Long id) {
        return scholarshipRepository.findById(id)
                .map(scholarship -> {
                    scholarship.setIsActive(!scholarship.getIsActive());
                    return scholarshipRepository.save(scholarship);
                })
                .orElseThrow(() -> new RuntimeException("Scholarship not found with id: " + id));
    }

    public List<Scholarship> searchScholarships(String keyword, Boolean active) {
    if (keyword != null && active != null) {
        return scholarshipRepository.findByNameContainingIgnoreCaseAndIsActive(keyword, active);
    } else if (keyword != null) {
        return scholarshipRepository.findByNameContainingIgnoreCase(keyword);
    } else if (active != null) {
        return scholarshipRepository.findByIsActive(active);
    }
    return scholarshipRepository.findAll();
}

}