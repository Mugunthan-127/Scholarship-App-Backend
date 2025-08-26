package com.examly.springapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.entity.FAQ;
import com.examly.springapp.repository.FAQRepository;
import com.examly.springapp.service.FAQService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/faq")
@CrossOrigin(origins = "https://scholarship-app-frontend.vercel.app")
public class FAQController {

    @Autowired
    private FAQService faqService;

    @Autowired
    private FAQRepository faqRepository;

    @PostMapping("/add")
    public FAQ addFAQ(@RequestBody FAQ faq) {
        return faqService.addFAQ(faq);
    }

    @GetMapping("/all")
    public List<FAQ> getAllFAQs() {
        return faqService.getAllFAQs();
    }

    @GetMapping("/search")
public ResponseEntity<?> searchFAQ(@RequestParam String question) {
    Optional<FAQ> faq = faqRepository.findByQuestionContainingIgnoreCase(question);
    if (faq.isPresent()) {
        return ResponseEntity.ok(faq.get());
    } else {
        return ResponseEntity.ok(Map.of("answer", "I don’t know the answer yet. Please contact admin."));
    }
}


    @PostMapping("/ask")
    public String askQuestion(@RequestBody Map<String, String> request) {
        String question = request.get("question");
        return faqService.getAnswer(question);
    }
}
