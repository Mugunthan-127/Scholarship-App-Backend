// StudentMapper.java
package com.examly.springapp.mapper;

import com.examly.springapp.dto.*;
import com.examly.springapp.entity.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentMapper {

    // Student mappings
    public StudentDTO toStudentDto(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        dto.setPhoneNumber(student.getPhoneNumber());
        dto.setDepartment(student.getDepartment());
        dto.setYearOfStudy(student.getYearOfStudy());
        dto.setCgpa(student.getCgpa());
        return dto;
    }

    public Student toStudentEntity(StudentDTO dto) {
        Student student = new Student();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setPhoneNumber(dto.getPhoneNumber());
        student.setDepartment(dto.getDepartment());
        student.setYearOfStudy(dto.getYearOfStudy());
        student.setCgpa(dto.getCgpa());
        return student;
    }

    // Scholarship mappings
    public ScholarshipDTO toScholarshipDto(Scholarship scholarship) {
        ScholarshipDTO dto = new ScholarshipDTO();
        dto.setId(scholarship.getId());
        dto.setName(scholarship.getName());
        dto.setDescription(scholarship.getDescription());
        dto.setAmount(scholarship.getAmount());
        dto.setDeadline(scholarship.getDeadline());
        dto.setCriteria(scholarship.getCriteria());
        dto.setIsActive(scholarship.getIsActive());
        return dto;
    }

    public Scholarship toScholarshipEntity(ScholarshipDTO dto) {
        Scholarship scholarship = new Scholarship();
        scholarship.setName(dto.getName());
        scholarship.setDescription(dto.getDescription());
        scholarship.setAmount(dto.getAmount());
        scholarship.setDeadline(dto.getDeadline());
        scholarship.setCriteria(dto.getCriteria());
        scholarship.setIsActive(dto.getIsActive());
        return scholarship;
    }

    // Application mappings
    public ApplicationDTO toApplicationDto(Application application) {
        ApplicationDTO dto = new ApplicationDTO();
        dto.setId(application.getId());
        dto.setStudentId(application.getStudent().getId());
        dto.setScholarship(toScholarshipDto(application.getScholarship()));
        dto.setApplicationDate(application.getApplicationDate());
        dto.setStatus(ApplicationStatusDTO.valueOf(application.getStatus().name()));
        dto.setDocuments(application.getDocuments());
        dto.setComments(application.getComments());
        return dto;
    }

    // List mappings
    public List<StudentDTO> toStudentDtoList(List<Student> students) {
        return students.stream().map(this::toStudentDto).collect(Collectors.toList());
    }
    
    public List<ScholarshipDTO> toScholarshipDtoList(List<Scholarship> scholarships) {
        return scholarships.stream().map(this::toScholarshipDto).collect(Collectors.toList());
    }
    
    public List<ApplicationDTO> toApplicationDtoList(List<Application> applications) {
        return applications.stream().map(this::toApplicationDto).collect(Collectors.toList());
    }
}