package com.example.timetablesystem.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonRootName("SessionDTO")
public class SessionDTO {
    private int sessionId;
    private String moduleTitle;
    private String batchTitle;
    private String roomName;
    private String lecturerName;
    private String day;
    private String lectureTime;
    private String lectureType;
}
