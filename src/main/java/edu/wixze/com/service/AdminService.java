package edu.wixze.com.service;

import edu.wixze.com.dto.Admin;

public interface AdminService {
    Admin addAdmin(Admin admin);
    Admin login(String username, String password);

    Admin findByUsername(String username);
}
