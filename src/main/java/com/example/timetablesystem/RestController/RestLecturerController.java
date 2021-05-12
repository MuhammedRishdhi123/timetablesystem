package com.example.timetablesystem.RestController;

import com.example.timetablesystem.dto.SessionDTO;
import com.example.timetablesystem.entities.Module;
import com.example.timetablesystem.entities.Session;
import com.example.timetablesystem.entities.User;
import com.example.timetablesystem.service.SessionService;
import com.example.timetablesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/lecturer")
public class RestLecturerController {
    @Autowired
    UserService userService;
    @Autowired
    SessionService sessionService;

    @GetMapping("/getTimetable/{userId}")
    public List<SessionDTO> getTimetable(@PathVariable(value = "userId") int userId)
    {
        User temp=userService.findUserById(userId);
        if(temp.getLecturer() != null){
            List<Session> sessionList=sessionService.getSessionsByBatchId(temp.getLecturer().getLecturerId());
            List<SessionDTO> sessionDTOS=new ArrayList<>();
            for(Session s:sessionList){
                SessionDTO sessionDTO=new SessionDTO();
                sessionDTO.setSessionId(s.getSessionId());
                sessionDTO.setModuleTitle(s.getModule().getModuleTitle());
                sessionDTO.setBatchTitle(s.getBatch().getBatchTitle());
                sessionDTO.setLecturerName(s.getLecturer().getUser().getName());
                sessionDTO.setRoomName(s.getRoom().getRoomName());
                sessionDTO.setDay(s.getDay().getDayName());
                sessionDTO.setLectureTime(s.getLectureTime().getLectureTimeName());
                sessionDTO.setLectureType(s.getLectureType().getLectureTypeName());
                sessionDTOS.add(sessionDTO);
            }
            return sessionDTOS;
        }
        return null;
    }


    @GetMapping("/getMyModules/{userId}")
    public List<Module> getMyModules(@PathVariable(value = "userId")int userId){
        User temp=userService.findUserById(userId);
        if(temp != null){
            List<Module> moduleList=temp.getLecturer().getModules().stream().collect(Collectors.toList());
            return moduleList;
        }
        return null;
    }

}
