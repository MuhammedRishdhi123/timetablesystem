package com.example.timetablesystem.service;

import com.example.timetablesystem.dto.CourseRegistration;
import com.example.timetablesystem.entities.Course;
import com.example.timetablesystem.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    private CourseRepository courseRepository;
    @Override
    public Course saveCourse(CourseRegistration course) {
        Course newcourse=new Course();
        newcourse.setCourseTitle(course.getCourseTitle());
        newcourse.setCourseDuration(course.getCourseDuration());
        courseRepository.save(newcourse);
        return newcourse;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public void deleteCourse(Course course) {
        courseRepository.delete(course);
    }

    @Override
    public Course getCourseById(int courseId) {
        return courseRepository.getOne(courseId);
    }

    @Override
    public Course getCourseByTitle(String title) {
        return courseRepository.findByCourseTitle(title);
    }

    @Override
    public boolean updateCourse(Course course) {
        Course newCourse=courseRepository.getOne(course.getCourseId());
        if(newCourse != null)
        {
            courseRepository.save(newCourse);
            return true;
        }
        return false;
    }
}
