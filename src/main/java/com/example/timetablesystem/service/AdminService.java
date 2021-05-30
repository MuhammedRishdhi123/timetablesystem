package com.example.timetablesystem.service;

import com.example.timetablesystem.dto.AdminDTO;
import com.example.timetablesystem.entities.Admin;


import java.util.List;

public interface AdminService {
    public Admin saveAdmin(AdminDTO adminDTO);
    public List<Admin> getAllAdmins();
    public void deleteAdmin(Admin admin);
    public Admin findByUserId(int userId);
}
