package com.example.timetablesystem.repository;

import com.example.timetablesystem.entities.Module;
import com.example.timetablesystem.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room,Integer> {
    @Query("SELECT room from Room room where room.roomName= ?1")
    Room findByRoomName(String name);
}
