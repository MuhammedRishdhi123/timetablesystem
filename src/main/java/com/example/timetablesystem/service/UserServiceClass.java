package com.example.timetablesystem.service;

import com.example.timetablesystem.model.User;
import com.example.timetablesystem.repository.UserRepository;

public class UserServiceClass implements UserService{
    private UserRepository userRepository;

    @Override
   public int addUser(User user){
        try{
           // User newuser=userRepository.searchByUsername(user.getName());
        }catch(Exception e)
        {

        }
        return 0;
    }
}
