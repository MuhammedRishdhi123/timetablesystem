package com.example.timetablesystem.dto;

import com.example.timetablesystem.entities.Batch;
import com.example.timetablesystem.entities.Lecturer;
import com.example.timetablesystem.entities.Module;
import com.example.timetablesystem.entities.Room;
import com.example.timetablesystem.entities.enums.Day;
import com.example.timetablesystem.entities.enums.LectureTime;
import com.example.timetablesystem.entities.enums.LectureType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Enumerated;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionRegistration {
    private Module module;
    private Room room;
    private int dayofweek;
    private int time;
    private int type;
    private Batch batch;
    private Lecturer lecturer;
}
