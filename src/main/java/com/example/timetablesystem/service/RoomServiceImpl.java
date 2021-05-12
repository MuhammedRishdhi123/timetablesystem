package com.example.timetablesystem.service;

import com.example.timetablesystem.dto.RoomRegistration;
import com.example.timetablesystem.entities.Module;
import com.example.timetablesystem.entities.Room;
import com.example.timetablesystem.entities.Session;
import com.example.timetablesystem.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService{
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Room saveRoom(RoomRegistration room) {
        Room newroom=new Room();
        newroom.setRoomName(room.getRoomName());
        newroom.setSeatingCapacity(room.getSeatingCapacity());
        newroom.setStatus(room.getStatus());
        roomRepository.save(newroom);
        return newroom;

    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public void deleteRoom(Room room) {
        roomRepository.delete(room);
    }

    @Override
    public Room getRoomById(int roomId) {
        return roomRepository.getOne(roomId);
    }

    @Override
    public boolean updateRoom(Room room) {
        Room isExist=roomRepository.getOne(room.getRoomId());
        if(isExist != null) {
            roomRepository.save(room);
            return true;
        }
        return false;
    }

    public boolean checkRoomAvailability(Session session) {
        List<Session> sessionList= session.getRoom().getSessions().stream().collect(Collectors.toList());
        for(Session s:sessionList){
            if(s.getDay()==session.getDay() && s.getLectureTime()==session.getLectureTime()) return false;
        }
        return true;
    }
}
