package com.example.timetablesystem.entities.enums;

public enum lectureType {
    LECTURE(1,"Lecture"),
    LABORATORY(2,"Lab");

    private int lectureType;
    private String lectureTypeName;

    lectureType(int lectureType, String lectureTypeName) {
        this.lectureType = lectureType;
        this.lectureTypeName = lectureTypeName;
    }

    public int getLectureType() {
        return lectureType;
    }

    public void setLectureType(int lectureType) {
        this.lectureType = lectureType;
    }

    public String getLectureTypeName() {
        return lectureTypeName;
    }

    public void setLectureTypeName(String lectureTypeName) {
        this.lectureTypeName = lectureTypeName;
    }
}
