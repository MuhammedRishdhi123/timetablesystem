package com.example.timetablesystem.repository;

import com.example.timetablesystem.entities.Lecturer;
import com.example.timetablesystem.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer,Integer> {
    @Query("SELECT lecturer from Lecturer lecturer where lecturer.lecturerId= ?1")
    Lecturer findByUserId(int userId);
}
