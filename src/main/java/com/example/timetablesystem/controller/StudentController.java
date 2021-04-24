package com.example.timetablesystem.controller;

import com.example.timetablesystem.entities.Session;
import com.example.timetablesystem.entities.User;
import com.example.timetablesystem.service.SessionService;
import com.example.timetablesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    SessionService sessionService;
    @Autowired
    UserService userService;

    @GetMapping("/home")
    public String getHomePage(Model model)
    {
        User loggedUser=userService.loggedUser();
        if(loggedUser != null){
            List<Session> sessionList=sessionService.getSessionsByBatchId(loggedUser.getStudent().getBatch().getBatchId());
            Collections.sort(sessionList, new Comparator<Session>() {
                @Override
                public int compare(Session o1, Session o2) {
                    if (o1.getDay() == o2.getDay()) {
                        return o1.getLectureTime().compareTo(o2.getLectureTime());
                    } else {
                        return o1.getDay().compareTo(o2.getDay());
                    }
                }
            });
            model.addAttribute("sessions",sessionList);
            model.addAttribute("sessionBatchTitle",loggedUser.getStudent().getBatch().getBatchTitle());
        }
        return "StudentHome";
    }


}
