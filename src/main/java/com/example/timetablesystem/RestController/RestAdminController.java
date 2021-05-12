package com.example.timetablesystem.RestController;

import com.example.timetablesystem.dto.SessionDTO;
import com.example.timetablesystem.entities.Session;
import com.example.timetablesystem.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class RestAdminController {

    @Autowired
    SessionService sessionService;

    @GetMapping("/getSessions/{batchTitle}")
    public List<SessionDTO> getBatchSessions(@PathVariable(value = "batchTitle")String batchTitle)
    {
        List<SessionDTO> sessions=new ArrayList<>();
        for(Session session:sessionService.getSessionsByBatchTitle(batchTitle)){
            SessionDTO dto=new SessionDTO();
            dto.setSessionId(session.getSessionId());
            dto.setModuleTitle(session.getModule().getModuleTitle());
            dto.setBatchTitle(session.getBatch().getBatchTitle());
            dto.setLecturerName(session.getLecturer().getUser().getName());
            dto.setDay(session.getDay().getDayName());
            dto.setLectureType(session.getLectureType().getLectureTypeName());
            dto.setRoomName(session.getRoom().getRoomName());
            dto.setLectureTime(session.getLectureTime().getLectureTimeName());
            sessions.add(dto);
        }
        return sessions;
    }
}
