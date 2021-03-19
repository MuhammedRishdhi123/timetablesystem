package com.example.timetablesystem.entities;

import com.example.timetablesystem.entities.enums.day;
import com.example.timetablesystem.entities.enums.lectureTime;
import com.example.timetablesystem.entities.enums.lectureType;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.sun.org.apache.xpath.internal.operations.Mod;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="session")
@JsonRootName("session")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sessionId;

    @ManyToMany
    @JoinTable(name = "batch_session",joinColumns = @JoinColumn(name="sessionId"),inverseJoinColumns = @JoinColumn(name = "batchId"))
    private Set<Batch> batches;

    @ManyToOne(fetch = FetchType.EAGER)
    private Room room;

    private com.example.timetablesystem.entities.enums.day day;

    private com.example.timetablesystem.entities.enums.lectureType lectureType;

    private com.example.timetablesystem.entities.enums.lectureTime lectureTime;

    @ManyToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinTable(name = "lecturer_session",joinColumns = @JoinColumn(name = "sessionId"),inverseJoinColumns = @JoinColumn(name="lecturerId"))
    private Set<Lecturer> lecturers;

    @ManyToOne(fetch = FetchType.EAGER)
    private Module module;

    public Session(Set<Batch> batches, com.example.timetablesystem.entities.enums.day day, com.example.timetablesystem.entities.enums.lectureType lectureType, com.example.timetablesystem.entities.enums.lectureTime lectureTime) {
        this.batches = batches;
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

    public Set<Batch> getBatches() {
        return batches;
    }

    public void setBatches(Set<Batch> batches) {
        this.batches = batches;
    }

    public com.example.timetablesystem.entities.enums.day getDay() {
        return day;
    }

    public void setDay(com.example.timetablesystem.entities.enums.day day) {
        this.day = day;
    }

    public com.example.timetablesystem.entities.enums.lectureType getLectureType() {
        return lectureType;
    }

    public void setLectureType(com.example.timetablesystem.entities.enums.lectureType lectureType) {
        this.lectureType = lectureType;
    }

    public com.example.timetablesystem.entities.enums.lectureTime getLectureTime() {
        return lectureTime;
    }

    public void setLectureTime(com.example.timetablesystem.entities.enums.lectureTime lectureTime) {
        this.lectureTime = lectureTime;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Set<Lecturer> getLecturers() {
        return lecturers;
    }

    public void setLecturers(Set<Lecturer> lecturers) {
        this.lecturers = lecturers;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public String getLecturersString() {
        StringBuilder sb = new StringBuilder();
        for (Lecturer t : lecturers) {
            sb.append(t.toString()).append(", ");
        }

        return sb.toString();
    }

    @Override
    public String toString() {
        return "Session{" +
                "sessionId=" + sessionId +
                ", batches=" + batches +
                ", day=" + day +
                ", lectureType=" + lectureType +
                ", lectureTime=" + lectureTime +
                '}';
    }
}
