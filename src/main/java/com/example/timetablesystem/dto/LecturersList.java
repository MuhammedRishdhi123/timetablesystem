package com.example.timetablesystem.dto;

import com.example.timetablesystem.entities.Lecturer;

import java.util.List;

public class LecturersList {
    private List<Lecturer> lecturerList;

    public LecturersList(List<Lecturer> lecturerList) {
        this.lecturerList = lecturerList;
    }

    public LecturersList() {
    }

    public List<Lecturer> getLecturerList() {
        return lecturerList;
    }

    public void setLecturerList(List<Lecturer> lecturerList) {
        this.lecturerList = lecturerList;
    }
}
