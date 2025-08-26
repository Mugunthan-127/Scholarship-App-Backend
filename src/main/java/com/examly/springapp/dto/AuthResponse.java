package com.examly.springapp.dto;

import com.examly.springapp.entity.Role;

public class AuthResponse {
    
    private boolean success;
    private String message;
    private String username;
    private String email;
    private Role role;
    private Long profileId;
    private Long userId;
    private String token;  // ✅ NEW field for JWT
    
    // Constructors
    public AuthResponse() {}

    public AuthResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public AuthResponse(boolean success, String username, String email, Role role, Long profileId, Long userId, String token) {
        this.success = success;
        this.username = username;
        this.email = email;
        this.role = role;
        this.profileId = profileId;
        this.userId = userId;
        this.token = token;
        this.message = "Authentication successful";
    }

    // Getters and Setters
    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    public Long getProfileId() {
        return profileId;
    }
    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
}
