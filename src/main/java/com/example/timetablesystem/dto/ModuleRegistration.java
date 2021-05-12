package com.example.timetablesystem.dto;

import com.example.timetablesystem.entities.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModuleRegistration {
    private String moduleTitle;
    private int moduleCredits;
    private Course course;

}
