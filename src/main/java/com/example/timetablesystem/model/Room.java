package com.example.timetablesystem.model;

import com.fasterxml.jackson.annotation.JsonRootName;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="Room")
@JsonRootName(value = "Room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomId;
    private String roomName;
    private int seatingCapacity;
    private String status;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "Room")
    private Set<TimetableItem> timetableItems;

    public Room(int roomId, int seatingCapacity, String roomName,String status) {
        this.roomId = roomId;
        this.seatingCapacity = seatingCapacity;
        this.roomName = roomName;
        this.status=status;
    }

    public Room() {

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", roomName='" + roomName + '\'' +
                ", seatingCapacity=" + seatingCapacity +
                ", status='" + status + '\'' +
                ", timetableItems=" + timetableItems +
                '}';
    }
}
