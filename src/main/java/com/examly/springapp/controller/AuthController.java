package com.examly.springapp.controller;

import com.examly.springapp.dto.AuthResponse;
import com.examly.springapp.dto.LoginRequest;
import com.examly.springapp.dto.SignupRequest;
import com.examly.springapp.entity.User;
import com.examly.springapp.repository.UserRepository;
import com.examly.springapp.service.AuthService;
import com.examly.springapp.service.EmailService;
import com.examly.springapp.service.PasswordResetTokenService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetTokenService passwordResetTokenService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@Valid @RequestBody SignupRequest request) {
        try {
            AuthResponse response = authService.signup(request);
            if (response.isSuccess()) {
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            AuthResponse errorResponse = new AuthResponse(false, "Registration failed: " + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        try {
            AuthResponse response = authService.login(request);
            if (response.isSuccess()) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            AuthResponse errorResponse = new AuthResponse(false, "Login failed: " + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

   @PostMapping("/forgot-password")
public String forgotPassword(@RequestParam String email) {
    User user = userRepository.findByEmail(email).orElse(null);
    if (user == null) return "User not found.";

    String token = passwordResetTokenService.createToken(user).getToken();

    // Replace with your email sending logic
    String resetLink = "http://localhost:3000/reset-password?token=" + token;
    emailService.sendForgotPasswordEmail(email, resetLink);

    return "Password reset link sent to your email.";
}

@PostMapping("/reset-password")
public String resetPassword(@RequestParam String token, @RequestParam String newPassword) {
    if (!passwordResetTokenService.isTokenValid(token)) return "Invalid or expired token.";

    User user = passwordResetTokenService.getUserByToken(token);
    if (user == null) return "Invalid token.";

    user.setPassword(passwordEncoder.encode(newPassword));
    userRepository.save(user);

    passwordResetTokenService.deleteToken(token); // remove after use
    return "Password reset successfully!";
}


    @PostMapping("/logout")
    public ResponseEntity<AuthResponse> logout() {
        AuthResponse response = new AuthResponse(true, "Logout successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
