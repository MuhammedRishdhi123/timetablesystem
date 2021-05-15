package com.example.timetablesystem.dto;

import com.example.timetablesystem.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LecturerDTO {
    private int lecturerId;
    private String lecturerName;
    private String lecturerEmail;
    private String lecturerPassword;
    private String lecturerPhone;
}
