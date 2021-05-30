package com.example.timetablesystem.service;


import com.example.timetablesystem.dto.AdminDTO;
import com.example.timetablesystem.entities.Admin;
import com.example.timetablesystem.entities.Role;
import com.example.timetablesystem.entities.User;
import com.example.timetablesystem.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;


    @Override
    public Admin saveAdmin(AdminDTO adminDTO) {
        Admin admin=new Admin();
        User user=new User();
        user.setName(adminDTO.getName());
        user.setPassword(encoder.encode(adminDTO.getPassword()));
        user.setPhone(adminDTO.getPhone());
        user.setEmail(adminDTO.getEmail());
        user.setRoles(Stream.of(new Role("Admin")).collect(Collectors.toSet()));

        admin.setUser(user);
        user.setAdmin(admin);
        return adminRepository.save(admin);
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }


    @Override
    public void deleteAdmin(Admin admin) {
        adminRepository.delete(admin);
    }

    @Override
    public Admin findByUserId(int userId) {
        return adminRepository.findByUserId(userId);
    }
}
