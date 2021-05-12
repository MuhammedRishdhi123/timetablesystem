package com.example.timetablesystem.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonRootName("Faculty")
public class FacultyDTO {
    private String name;
    private String email;
    private String contactNumber;

}
