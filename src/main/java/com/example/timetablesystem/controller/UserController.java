package com.example.timetablesystem.controller;

import com.example.timetablesystem.dto.AdminRegistration;
import com.example.timetablesystem.dto.StudentRegistration;
import com.example.timetablesystem.entities.*;
import com.example.timetablesystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private BatchService batchService;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @GetMapping("/registerStudent")
    public String getPatientRegisterPage(Model model){
        List<Course> courses=courseService.getAllCourses();
        List<Batch> batches=batchService.getAllBatches();
        model.addAttribute("student",new StudentRegistration());
        model.addAttribute("batches",batches);
        model.addAttribute("courses",courses);
        return "registerStudent";
    }



    @PostMapping("/saveStudent")
    public String RegisterStudent(@ModelAttribute("student")StudentRegistration studentRegistration)
    {

        boolean emailExist=userService.checkEmail(studentRegistration.getEmail());
        if(emailExist){
            return "redirect:/user/registerStudent?invalidEmail";
        }
        else if(!emailExist){
            studentService.saveStudent(studentRegistration);
            return "redirect:/user/registerStudent?success";

        }
        return null;
    }

    @GetMapping("/profile")
    public String getProfile(Model model)
    {
        User loggedUser=userService.loggedUser();

        if(loggedUser != null)
        {
            model.addAttribute("user",loggedUser);
//            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();

//            if(authentication.getAuthorities().stream().anyMatch(r->r.getAuthority().equals("Student")))
//            {
//                Student student= studentService.findByUserId(loggedUser.getUserId());
//                model.addAttribute("student",student);
//            }
//            if(authentication.getAuthorities().stream().anyMatch(r->r.getAuthority().equals("Admin")))
//            {
//                Admin admin= adminService.findByUserId(loggedUser.getUserId());
//                model.addAttribute("admin",admin);
//            }
//            if(authentication.getAuthorities().stream().anyMatch(r->r.getAuthority().equals("Lecturer")))
//            {
//                Lecturer lecturer= lecturerService.findByUserId(loggedUser.getUserId());
//                model.addAttribute("lecturer",lecturer);
//            }


        }
        return "profile";
    }

    @PostMapping("/updateuser")
    public String updateUser(@ModelAttribute("user")User user)
    {
        boolean isUpdated=false;
        User loggedUser=userService.findUserById(user.getUserId());
        loggedUser.setName(user.getName());
        loggedUser.setPhone(user.getPhone());
        if(user.getPassword() != null)
        {
            loggedUser.setPassword(encoder.encode(user.getPassword()));
        }
        isUpdated=userService.updateUser(loggedUser);
        if(isUpdated)
        {
            return "redirect:/user/profile?updated";
        }
        return "redirect:/user/profile?failed";
    }

}
