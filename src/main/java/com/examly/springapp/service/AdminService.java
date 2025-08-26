package com.examly.springapp.service;

import com.examly.springapp.entity.Admin;
import com.examly.springapp.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    
    @Autowired
    private AdminRepository adminRepository;
    
    public Admin createAdmin(Admin admin) {
        if (adminRepository.existsByEmail(admin.getEmail())) {
            throw new RuntimeException("Admin with this email already exists");
        }

        // ✅ Check duplicate phone number
        if (adminRepository.existsByPhoneNumber(admin.getPhoneNumber())) {
            throw new RuntimeException("Admin with this phone number already exists");
        }

        return adminRepository.save(admin);
    }
    
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }
    
    public Optional<Admin> getAdminById(Long id) {
        return adminRepository.findById(id);
    }
    
    public Optional<Admin> getAdminByEmail(String email) {
        return adminRepository.findByEmail(email);
    }
    
    public Admin updateAdmin(Long id, Admin updatedAdmin) {
        return adminRepository.findById(id)
                .map(admin -> {
                    admin.setName(updatedAdmin.getName());
                    admin.setEmail(updatedAdmin.getEmail());
                    admin.setPhoneNumber(updatedAdmin.getPhoneNumber());
                    admin.setDepartment(updatedAdmin.getDepartment());
                    return adminRepository.save(admin);
                })
                .orElseThrow(() -> new RuntimeException("Admin not found with id: " + id));
    }
    
    public void deleteAdmin(Long id) {
        if (!adminRepository.existsById(id)) {
            throw new RuntimeException("Admin not found with id: " + id);
        }
        adminRepository.deleteById(id);
    }
}
