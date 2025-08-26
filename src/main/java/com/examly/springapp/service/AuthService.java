package com.examly.springapp.service;

import com.examly.springapp.entity.*;
import com.examly.springapp.repository.*;
import com.examly.springapp.dto.*;
import com.examly.springapp.security.JwtUtil;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private AdminRepository adminRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;   // ✅ Inject JWT util

    @Value("${admin.registration.secret}")
private String adminSecretConfig;
 // 🔑 Admin secret from properties
    
    public AuthResponse signup(SignupRequest request) {
        try {
            if (userRepository.existsByUsername(request.getUsername())) {
                return new AuthResponse(false, "Username already exists");
            }
            if (userRepository.existsByEmail(request.getEmail())) {
                return new AuthResponse(false, "Email already exists");
            }
            if (request.getRole() == Role.STUDENT && studentRepository.existsByEmail(request.getEmail())) {
                return new AuthResponse(false, "Email already exists in student records");
            }
            if (request.getRole() == Role.ADMIN && adminRepository.existsByEmail(request.getEmail())) {
                return new AuthResponse(false, "Email already exists in admin records");
            }

            // 🔑 Admin secret check
            if (request.getRole() == Role.ADMIN) {
                if (request.getAdminSecret() == null || !request.getAdminSecret().equals(adminSecretConfig)) {
                    return new AuthResponse(false, "Invalid admin secret. Registration denied.");
                }
            }

            // Create user
            User user = new User();
            user.setUsername(request.getUsername());
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setRole(request.getRole());

            User savedUser = userRepository.save(user);
            Long profileId = null;

            if (request.getRole() == Role.STUDENT) {
                Student student = new Student();
                student.setName(request.getName());
                student.setEmail(request.getEmail());
                student.setPhoneNumber(request.getPhoneNumber());
                student.setDepartment(request.getDepartment());
                student.setYearOfStudy(request.getYearOfStudy());
                student.setCgpa(request.getCgpa());
                profileId = studentRepository.save(student).getId();
            } else if (request.getRole() == Role.ADMIN) {
                Admin admin = new Admin();
                admin.setName(request.getName());
                admin.setEmail(request.getEmail());
                admin.setPhoneNumber(request.getPhoneNumber());
                admin.setDepartment(request.getDepartment());
                profileId = adminRepository.save(admin).getId();
            }

            return new AuthResponse(true, savedUser.getUsername(), savedUser.getEmail(),
                    savedUser.getRole(), profileId, savedUser.getId(), null); // signup returns no token
        } catch (Exception e) {
            return new AuthResponse(false, "Registration failed: " + e.getMessage());
        }
    }
//     @PostConstruct
// public void checkSecret() {
//     System.out.println("Admin secret is: " + adminSecretConfig);
//}

    
    public AuthResponse login(LoginRequest request) {
        try {
            User user = userRepository.findByUsername(request.getUsernameOrEmail())
                    .orElse(userRepository.findByEmail(request.getUsernameOrEmail())
                    .orElse(null));

            if (user == null) {
                return new AuthResponse(false, "Invalid username/email or password");
            }
            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                return new AuthResponse(false, "Invalid username/email or password");
            }
            if (!user.getEnabled()) {
                return new AuthResponse(false, "Account is disabled");
            }

            Long profileId = null;
            if (user.getRole() == Role.STUDENT) {
                profileId = studentRepository.findByEmail(user.getEmail())
                        .map(Student::getId).orElse(null);
            } else if (user.getRole() == Role.ADMIN) {
                profileId = adminRepository.findByEmail(user.getEmail())
                        .map(Admin::getId).orElse(null);
            }

            // ✅ Generate JWT token
            String token = jwtUtil.generateToken(user.getUsername(), user.getRole().name());

            return new AuthResponse(true, user.getUsername(), user.getEmail(),
                    user.getRole(), profileId, user.getId(), token);
        } catch (Exception e) {
            return new AuthResponse(false, "Login failed: " + e.getMessage());
        }
    }
}
