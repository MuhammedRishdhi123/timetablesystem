package com.example.timetablesystem.api;

import com.example.timetablesystem.model.User;
import com.example.timetablesystem.service.UserService;

public class UserController {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void addUser(User user){
        userService.addUser(user);
    }
}
