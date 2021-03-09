package com.example.timetablesystem.model;

public class Room {
    private int roomId,seatingCapacity;
    private String roomName;

    public Room(int roomId, int seatingCapacity, String roomName) {
        this.roomId = roomId;
        this.seatingCapacity = seatingCapacity;
        this.roomName = roomName;
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
}
