package com.example.timetablesystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomRegistration {
    private String roomName;
    private int seatingCapacity;
    private String status;
}
