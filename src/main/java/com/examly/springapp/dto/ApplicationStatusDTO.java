// ApplicationStatusDTO.java
package com.examly.springapp.dto;

public enum ApplicationStatusDTO {
    PENDING,
    APPROVED,
    REJECTED,
    UNDER_REVIEW;

    // Add conversion methods if needed
    public static ApplicationStatusDTO fromEntity(com.examly.springapp.entity.ApplicationStatus status) {
        return ApplicationStatusDTO.valueOf(status.name());
    }

    public com.examly.springapp.entity.ApplicationStatus toEntity() {
        return com.examly.springapp.entity.ApplicationStatus.valueOf(this.name());
    }
}