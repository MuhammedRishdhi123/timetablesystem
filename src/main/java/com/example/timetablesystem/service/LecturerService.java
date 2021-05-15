package com.example.timetablesystem.service;

import com.example.timetablesystem.dto.LecturerDTO;
import com.example.timetablesystem.entities.Lecturer;
import com.example.timetablesystem.entities.Session;

import java.util.List;

public interface LecturerService {
    public Lecturer saveLecturer(LecturerDTO lecturerDTO);
    public List<Lecturer> getAllLecturer();
    public Lecturer findByUserId(int userId);
    public Lecturer findByLecturerId(int lecturerId);
    public void delete(Lecturer lecturer);
    public boolean checkLecturerAvailability(Session session);
    public Lecturer getLecturerByName(String name);
}
