package com.example.timetablesystem.service;

import com.example.timetablesystem.dto.LecturerRegistration;
import com.example.timetablesystem.entities.Lecturer;
import com.example.timetablesystem.entities.Session;
import com.example.timetablesystem.entities.Student;
import com.example.timetablesystem.entities.enums.Day;
import com.example.timetablesystem.entities.enums.LectureTime;

import java.util.List;

public interface LecturerService {
    public Lecturer saveLecturer(LecturerRegistration lecturerRegistration);
    public List<Lecturer> getAllLecturer();
    public Lecturer findByUserId(int userId);
    public void delete(Lecturer lecturer);
    public boolean checkLecturerAvailability(Session session);
}
