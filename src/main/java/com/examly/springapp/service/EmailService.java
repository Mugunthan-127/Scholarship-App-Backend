package com.examly.springapp.service;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
    public void sendForgotPasswordEmail(String toEmail, String resetLink) {
        // For now just log to console
        System.out.println("Send email to: " + toEmail);
        System.out.println("Password reset link: " + resetLink);
    }
}
