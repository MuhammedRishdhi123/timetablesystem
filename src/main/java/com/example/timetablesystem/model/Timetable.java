package com.example.timetablesystem.model;

public class Timetable {
    private int timetableId;
    private String batchCode;
    private String courseTitle;

    public Timetable(int timetableId, String batchCode, String courseTitle) {
        this.timetableId = timetableId;
        this.batchCode = batchCode;
        this.courseTitle = courseTitle;
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
}
