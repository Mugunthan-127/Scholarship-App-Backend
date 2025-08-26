package com.examly.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examly.springapp.entity.FAQ;

import java.util.Optional;

public interface FAQRepository extends JpaRepository<FAQ, Long> {
    Optional<FAQ> findByQuestionIgnoreCase(String question);
     Optional<FAQ> findByQuestionContainingIgnoreCase(String question);
}
