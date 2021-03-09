package com.example.timetablesystem.model;

public class Course {
    private int courseId,courseDuration;
    private String courseTitle;

    public Course(int courseId, int courseDuration, String courseTitle) {
        this.courseId = courseId;
        this.courseDuration = courseDuration;
        this.courseTitle = courseTitle;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(int courseDuration) {
        this.courseDuration = courseDuration;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }
}
