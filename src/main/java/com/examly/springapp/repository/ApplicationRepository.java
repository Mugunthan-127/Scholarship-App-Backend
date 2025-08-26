package com.examly.springapp.repository;

import com.examly.springapp.entity.Application;
import com.examly.springapp.entity.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    
    List<Application> findByStatus(ApplicationStatus status);
    
    List<Application> findByStudentId(Long studentId);
    
    List<Application> findByScholarshipId(Long scholarshipId);
    
    boolean existsByStudentIdAndScholarshipId(Long studentId, Long scholarshipId);
}