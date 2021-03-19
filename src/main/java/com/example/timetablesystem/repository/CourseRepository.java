package com.example.timetablesystem.repository;

import com.example.timetablesystem.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {
}
