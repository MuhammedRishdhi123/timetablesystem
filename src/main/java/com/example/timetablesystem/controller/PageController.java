package com.example.timetablesystem.controller;

import com.example.timetablesystem.dto.StudentDTO;
import com.example.timetablesystem.entities.Batch;
import com.example.timetablesystem.entities.Course;
import com.example.timetablesystem.entities.Session;
import com.example.timetablesystem.entities.User;
import com.example.timetablesystem.service.BatchService;
import com.example.timetablesystem.service.CourseService;
import com.example.timetablesystem.service.SessionService;
import com.example.timetablesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class PageController {
    @Autowired
    UserService userService;
    @Autowired
    SessionService sessionService;
    @Autowired
    CourseService courseService;
    @Autowired
    BatchService batchService;

    @GetMapping("/login")
    public String getLoginPage()
    {
        return "login";
    }

    @GetMapping("/error")
    public String getErrorPage()
    {
        return "error";
    }

    @GetMapping("/")
    public String getHomePage()
    {
        User user=userService.loggedUser();

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getAuthorities().stream().anyMatch(role->role.getAuthority().equals("Student")))
        {

            return "redirect:/student/home";
        }
        if(authentication.getAuthorities().stream().anyMatch(role->role.getAuthority().equals("Lecturer")))
        {
            return "redirect:/lecturer/home";
        }
        if(authentication.getAuthorities().stream().anyMatch(role->role.getAuthority().equals("Admin")))
        {
            return "redirect:/admin/home";
        }
     return "index";
    }


    @GetMapping("/registerStudent")
    public String getStudentRegisterPage(Model model){
        List<Course> courses=courseService.getAllCourses();
        List<Batch> batches=batchService.getAllBatches();
        model.addAttribute("student",new StudentDTO());
        model.addAttribute("batches",batches);
        model.addAttribute("courses",courses);
        return "registerStudent";
    }

}
