package com.example.timetablesystem.controller;

import com.example.timetablesystem.entities.*;
import com.example.timetablesystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder encoder;


    @GetMapping("/profile")
    public String getProfile(Model model)
    {
        User loggedUser=userService.loggedUser();

        if(loggedUser != null)
        {
            model.addAttribute("user",loggedUser);
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
