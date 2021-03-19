package com.example.timetablesystem.service;

import com.example.timetablesystem.dto.AdminRegistration;
import com.example.timetablesystem.entities.Admin;


import java.util.List;
import java.util.Set;

public interface AdminService {
    public Admin saveAdmin(AdminRegistration adminRegistration);
    public List<Admin> getAllAdmins();
    public void deleteAdmin(Admin admin);
    public Admin findByUserId(int userId);
}
