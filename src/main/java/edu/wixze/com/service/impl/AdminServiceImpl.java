package edu.wixze.com.service.impl;

import edu.wixze.com.dto.Admin;
import edu.wixze.com.entity.AdminEntity;
import edu.wixze.com.repository.AdminRepository;
import edu.wixze.com.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;  // <-- make final

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Admin addAdmin(Admin admin) {
        AdminEntity entity = new AdminEntity();
        entity.setName(admin.getName());
        entity.setUsername(admin.getUsername());
        entity.setPassword(passwordEncoder.encode(admin.getPassword()));

        AdminEntity saved = adminRepository.save(entity);

        // Return DTO without password
        return new Admin(saved.getId(), saved.getName(), saved.getUsername(), null, "admin");
    }

    @Override
    public Admin login(String username, String rawPassword) {
        AdminEntity entity = adminRepository.findByUsername(username).orElse(null);
        if (entity != null && passwordEncoder.matches(rawPassword, entity.getPassword())) {
            return new Admin(entity.getId(), entity.getName(), entity.getUsername(), null, "admin");
        }
        return null; // or throw an exception
    }

    @Override
    public Admin findByUsername(String username) {
        AdminEntity entity = adminRepository.findByUsername(username).orElse(null);
        if (entity == null) return null;
        return new Admin(entity.getId(), entity.getName(), entity.getUsername(), null, "admin");
    }
}