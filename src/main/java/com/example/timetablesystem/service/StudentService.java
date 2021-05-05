package com.example.timetablesystem.service;

import com.example.timetablesystem.dto.StudentDTO;
import com.example.timetablesystem.entities.Student;

import java.util.List;
import java.util.Set;

public interface StudentService {
    public Student saveStudent(StudentDTO studentDTO);
    public List<Student> getAllStudents();
    public Student findByUserId(int userId);
    public void delete(Student student);
}
