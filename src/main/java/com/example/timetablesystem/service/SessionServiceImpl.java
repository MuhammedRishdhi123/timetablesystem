package com.example.timetablesystem.service;

import com.example.timetablesystem.dto.SessionRegistration;
import com.example.timetablesystem.entities.Batch;
import com.example.timetablesystem.entities.Lecturer;
import com.example.timetablesystem.entities.Session;
import com.example.timetablesystem.entities.enums.Day;
import com.example.timetablesystem.entities.enums.LectureTime;
import com.example.timetablesystem.entities.enums.LectureType;
import com.example.timetablesystem.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessionServiceImpl implements SessionService{

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public Session saveSession(Session session) {
       Session newsession=new Session();
       newsession.setModule(session.getModule());
       newsession.setBatch(session.getBatch());
       newsession.setDay(session.getDay());
       newsession.setLectureTime(session.getLectureTime());
       newsession.setLecturer(session.getLecturer());
       newsession.setLectureType(session.getLectureType());
       newsession.setRoom(session.getRoom());
       sessionRepository.save(newsession);
       return session;
    }

    @Override
    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    @Override
    public Session getSessionById(int sessionId) {
        return sessionRepository.getOne(sessionId);
    }

    @Override
    public List<Session> getSessionsByBatchId(int batchId) {
        return sessionRepository.getBatchSessions(batchId);
    }

    @Override
    public List<Session> getSessionsByBatchTitle(String title) {
        return sessionRepository.getBatchSessions(title);
    }

    @Override
    public void deleteSession(Session session) {
        sessionRepository.delete(session);
    }

    @Override
    public List<Session> getSessionsByLecturerId(int lecturerId) {
        return sessionRepository.getLecturerSessions(lecturerId);
    }

    @Override
    public boolean checkSessionAvailability(Session session) {
        Batch batch=session.getBatch();
        List<Session> sessionList=batch.getSessions().stream().collect(Collectors.toList());
        for(Session s:sessionList){
            if(s.getDay()==session.getDay() && s.getLectureTime()==session.getLectureTime()) return false;
        }
        return true;
    }


    private LectureType getLectureType(int index)
    {
        if(index == 1) return LectureType.LECTURE;
        if(index == 2) return LectureType.LABORATORY;
        else return LectureType.LECTURE;
    }

    private LectureTime getLectureTime(int index)
    {
        if(index == 1) return LectureTime.FIRST;
        if(index == 2) return LectureTime.SECOND;
        if(index == 3) return LectureTime.THIRD;
        if(index == 4) return LectureTime.FOURTH;
        if(index == 5) return LectureTime.FIFTH;
        else return null;
    }

    private Day getDay(int index)
    {
        if(index == 1) return Day.MONDAY;
        if(index == 2) return Day.TUESDAY;
        if(index == 3) return Day.WEDNESDAY;
        if(index == 4) return Day.THURSDAY;
        if(index == 5) return Day.FRIDAY;
        else return null;
    }
}
