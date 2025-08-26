package com.examly.springapp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.entity.FAQ;
import com.examly.springapp.repository.FAQRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FAQService {

    @Autowired
    private FAQRepository faqRepository;

    public FAQ addFAQ(FAQ faq) {
        return faqRepository.save(faq);
    }

    public List<FAQ> getAllFAQs() {
        return faqRepository.findAll();
    }

    public String getAnswer(String question) {
        Optional<FAQ> faq = faqRepository.findByQuestionIgnoreCase(question);
        return faq.map(FAQ::getAnswer).orElse("Sorry, I don’t know the answer to that question.");
    }
}
