package com.example.timetablesystem.service;

import com.example.timetablesystem.dto.SessionRegistration;
import com.example.timetablesystem.entities.Session;

import java.util.List;

public interface SessionService {
    public Session saveSession(Session session);
    public List<Session> getAllSessions();
    public Session getSessionById(int sessionId);
    public List<Session> getSessionsByBatchId(int batchId);
    public List<Session> getSessionsByBatchTitle(String title);
    public void deleteSession(Session session);
    public List<Session> getSessionsByLecturerId(int lecturerId);
    public boolean checkSessionAvailability(Session session);
}
