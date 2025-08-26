package com.examly.springapp.repository;

import com.examly.springapp.entity.Scholarship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ScholarshipRepository extends JpaRepository<Scholarship, Long> {
    
    List<Scholarship> findByIsActiveTrue();
    
    List<Scholarship> findByIsActive(Boolean isActive);

    List<Scholarship> findByNameContainingIgnoreCase(String name);

List<Scholarship> findByNameContainingIgnoreCaseAndIsActive(String name, Boolean isActive);

}