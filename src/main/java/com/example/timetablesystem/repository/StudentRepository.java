package com.example.timetablesystem.repository;

import com.example.timetablesystem.entities.Student;
import com.example.timetablesystem.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    @Query("SELECT student from Student student where student.studentId= ?1")
    Student findByUserId(int userId);
}
