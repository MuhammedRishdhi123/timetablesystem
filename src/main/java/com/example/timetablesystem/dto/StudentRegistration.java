package com.example.timetablesystem.dto;


import com.example.timetablesystem.entities.Batch;
import com.example.timetablesystem.entities.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRegistration {
    private String name;
    private String email;
    private String password;
    private String phone;
    private Course course;
    private Batch batch;


}
