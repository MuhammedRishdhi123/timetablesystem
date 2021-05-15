package com.example.timetablesystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {
    private int roomId;
    private String roomName;
    private int seatingCapacity;
    private String status;
}
