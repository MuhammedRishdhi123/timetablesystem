package com.example.timetablesystem.service;

import com.example.timetablesystem.dto.CourseRegistration;
import com.example.timetablesystem.dto.RoomRegistration;
import com.example.timetablesystem.entities.Course;
import com.example.timetablesystem.entities.Room;
import com.example.timetablesystem.entities.Session;

import java.util.List;

public interface RoomService {
    public Room saveRoom(RoomRegistration room);
    public List<Room> getAllRooms();
    public void deleteRoom(Room room);
    public Room getRoomById(int roomId);
    public boolean updateRoom(Room room);
    public boolean checkRoomAvailability(Session session);
}
