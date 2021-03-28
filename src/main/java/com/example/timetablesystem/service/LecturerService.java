package com.example.timetablesystem.service;

import com.example.timetablesystem.dto.LecturerRegistration;
import com.example.timetablesystem.dto.StudentRegistration;
import com.example.timetablesystem.entities.Lecturer;
import com.example.timetablesystem.entities.Student;

import java.util.List;

public interface LecturerService {
    public Lecturer saveLecturer(LecturerRegistration lecturerRegistration);
    public List<Lecturer> getAllLecturer();
    public Lecturer findByUserId(int userId);
    public void delete(Lecturer lecturer);
}
