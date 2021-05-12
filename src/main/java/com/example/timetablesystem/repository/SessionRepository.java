package com.example.timetablesystem.repository;

import com.example.timetablesystem.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session,Integer> {
    @Query("SELECT s from Session s WHERE s.batch.batchId=?1")
    public List<Session> getBatchSessions(int batchId);
    @Query("SELECT s from Session s WHERE s.lecturer.lecturerId=?1")
    public List<Session> getLecturerSessions(int lecturerId);
    @Query("SELECT s from Session s WHERE s.batch.batchTitle=?1")
    public List<Session> getBatchSessions(String title);


}
