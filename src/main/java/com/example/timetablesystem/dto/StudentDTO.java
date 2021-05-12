package com.example.timetablesystem.dto;


import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonRootName("StudentDTO")
public class StudentDTO {
    private int studentId;
    private String studentName;
    private String studentEmail;
    private String courseName;
    private String batchTitle;
    private String studentPhone;
    private String password;

}
