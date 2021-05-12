package com.example.timetablesystem.controller;

import com.example.timetablesystem.dto.StudentDTO;
import com.example.timetablesystem.entities.Lecturer;
import com.example.timetablesystem.entities.Module;
import com.example.timetablesystem.entities.Session;
import com.example.timetablesystem.entities.User;
import com.example.timetablesystem.service.SessionService;
import com.example.timetablesystem.service.StudentService;
import com.example.timetablesystem.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    SessionService sessionService;
    @Autowired
    UserService userService;
    @Autowired
    StudentService studentService;

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

    @GetMapping("/myFaculty")
    public String myFaculty(Model model)
    {
        User loggedUser=userService.loggedUser();
        if(loggedUser.getStudent() != null){
            List<Module> moduleList=loggedUser.getStudent().getCourse().getModules().stream().collect(Collectors.toList());
            List<Lecturer> lecturerList=new ArrayList<>();
            for(Module m:moduleList){
                for(Lecturer l:m.getLecturers()){
                    lecturerList.add(l);
                }
            }
            model.addAttribute("lecturers",lecturerList);
            return "myFaculty";
        }
        return null;
    }

    @GetMapping("/myModules")
    public String myModules(Model model)
    {
        User loggedUser=userService.loggedUser();
         if(loggedUser.getStudent() != null){
            List<Module> moduleList=loggedUser.getStudent().getCourse().getModules().stream().collect(Collectors.toList());
            model.addAttribute("modules",moduleList);
            return "myModules";
        }
        return null;
    }


    @PostMapping("/saveStudent")
    public String RegisterStudent(@ModelAttribute("student") StudentDTO studentDTO)
    {

        boolean isExist=userService.checkEmail(studentDTO.getStudentEmail());
        if(isExist){
            return "redirect:/user/registerStudent?invalidEmail";
        }
        else if(!isExist){
            studentService.saveStudent(studentDTO);
            return "redirect:/user/registerStudent?success";

        }
        return null;
    }


}
