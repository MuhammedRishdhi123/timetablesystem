package com.example.timetablesystem.dto;

import com.example.timetablesystem.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LecturerRegistration {
    private String name;
    private String email;
    private String password;
    private String phone;
}
