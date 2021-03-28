package com.example.timetablesystem.entities;

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

    @OneToMany(mappedBy = "room")
    private Set<Session> sessions;

    public Room(int roomId, String roomName, int seatingCapacity, String status) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.seatingCapacity = seatingCapacity;
        this.status = status;
    }

    public Room() {
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Session> getSessions() {
        return sessions;
    }

    public void setSessions(Set<Session> sessions) {
        this.sessions = sessions;
    }

    public String getSessionsString() {
        StringBuilder sb = new StringBuilder();
        for (Session s : sessions) {
            sb.append(s.toString()).append(", ");
        }

        return sb.toString();
    }


    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", roomName='" + roomName + '\'' +
                ", seatingCapacity=" + seatingCapacity +
                ", status='" + status + '\'' +
                '}';
    }
}
