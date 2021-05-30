package com.example.timetablesystem.service;

import com.example.timetablesystem.dto.CourseDTO;
import com.example.timetablesystem.entities.Course;

import java.util.List;

public interface CourseService {
    public Course saveCourse(CourseDTO course);
    public List<Course> getAllCourses();
    public void deleteCourse(Course course);
    public Course getCourseById(int courseId);
    public Course getCourseByTitle(String title);
    public boolean updateCourse(Course course);
}
