package com.example.timetablesystem.entities;

import com.example.timetablesystem.entities.enums.Day;
import com.example.timetablesystem.entities.enums.LectureTime;
import com.example.timetablesystem.entities.enums.LectureType;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="session")
@JsonRootName("session")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sessionId;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "batchId")
    private Batch batch;

    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "roomId")
    private Room room;

    @Enumerated(EnumType.STRING)
    private Day day;

    @Enumerated(EnumType.STRING)
    private LectureType lectureType;

    @Enumerated(EnumType.STRING)
    private LectureTime lectureTime;

    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinTable(name = "session_lecturer",joinColumns = @JoinColumn(name = "sessionId"),inverseJoinColumns = @JoinColumn(name = "lecturerId"))
    private Lecturer lecturer;

    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "moduleId")
    private Module module;

    public Session(Batch batch, Day day, LectureType lectureType, LectureTime lectureTime) {
        this.batch = batch;
        this.day = day;
        this.lectureType = lectureType;
        this.lectureTime = lectureTime;
    }

    public Session() {
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public LectureType getLectureType() {
        return lectureType;
    }

    public void setLectureType(LectureType lectureType) {
        this.lectureType = lectureType;
    }

    public LectureTime getLectureTime() {
        return lectureTime;
    }

    public void setLectureTime(LectureTime lectureTime) {
        this.lectureTime = lectureTime;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    @Override
    public String toString() {
        return "Session{" +
                "sessionId=" + sessionId +
                ", batch=" + batch +
                ", room=" + room +
                ", day=" + day +
                ", lectureType=" + lectureType +
                ", lectureTime=" + lectureTime +
                ", lecturer=" + lecturer +
                ", module=" + module +
                '}';
    }
}
