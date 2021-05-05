package com.example.timetablesystem.repository;

import com.example.timetablesystem.entities.Batch;
import com.example.timetablesystem.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {
    @Query("SELECT course from Course course where course.courseTitle= ?1")
    Course findByCourseTitle(String title);
}
