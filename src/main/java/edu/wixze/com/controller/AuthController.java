package edu.wixze.com.controller;


import edu.wixze.com.dto.Admin;
import edu.wixze.com.security.JwtUtil;
import edu.wixze.com.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AdminService adminService;
    private final JwtUtil jwtUtil;


    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Admin admin) {
        Admin loggedIn = adminService.login(admin.getUsername(), admin.getPassword());
        if (loggedIn == null) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(loggedIn.getUsername());

        Map<String, Object> response = new HashMap<>();
        response.put("admin", loggedIn);
        response.put("token", token);
        return response;
    }

    @PostMapping("/signup")
    public Map<String, Object> signup(@RequestBody Admin admin) {
        Admin existing = adminService.findByUsername(admin.getUsername());
        if (existing != null) {
            throw new RuntimeException("Username already exists");
        }

        Admin savedAdmin = adminService.addAdmin(admin);

        String token = jwtUtil.generateToken(savedAdmin.getUsername());

        Map<String, Object> response = new HashMap<>();
        response.put("admin", savedAdmin);
        response.put("token", token);
        return response;
    }
}