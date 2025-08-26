package com.examly.springapp.entity;

import jakarta.persistence.*;

@Entity
public class FAQ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String question;

    @Column(nullable = false, length = 1000)
    private String answer;

    // Constructors
    public FAQ() {}
    public FAQ(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }

    public String getAnswer() { return answer; }
    public void setAnswer(String answer) { this.answer = answer; }
}
