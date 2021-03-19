package com.example.timetablesystem.controller;

import com.example.timetablesystem.entities.User;
import com.example.timetablesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PageController {
    @Autowired
    UserService userService;

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



}
