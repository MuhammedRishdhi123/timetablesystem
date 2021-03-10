package com.example.timetablesystem.model;

import com.fasterxml.jackson.annotation.JsonRootName;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="Timetable")
@JsonRootName(value = "Timetable")
public class Timetable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int timetableId;
    private String batchCode;
    private String courseTitle;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "Timetable")
    private Set<TimetableItem> timetableItems;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "Timetable")
    private Set<Student> students;

    public Timetable(int timetableId, String batchCode, String courseTitle) {
        this.timetableId = timetableId;
        this.batchCode = batchCode;
        this.courseTitle = courseTitle;
    }

    public Timetable() {

    }

    public int getTimetableId() {
        return timetableId;
    }

    public void setTimetableId(int timetableId) {
        this.timetableId = timetableId;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    @Override
    public String toString() {
        return "Timetable{" +
                "timetableId=" + timetableId +
                ", batchCode='" + batchCode + '\'' +
                ", courseTitle='" + courseTitle + '\'' +
                ", timetableItems=" + timetableItems +
                ", students=" + students +
                '}';
    }
}
