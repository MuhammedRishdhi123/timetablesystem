package com.example.timetablesystem.service;

import com.example.timetablesystem.dto.CourseRegistration;
import com.example.timetablesystem.entities.Course;

import java.util.List;
import java.util.Set;

public interface CourseService {
    public Course saveCourse(CourseRegistration course);
    public List<Course> getAllCourses();
    public void deleteCourse(Course course);
    public Course getCourseById(int courseId);
    public boolean updateCourse(Course course);
}
