package com.example.timetablesystem.RestController;

import com.example.timetablesystem.dto.RestLogin;
import com.example.timetablesystem.dto.SessionDTO;
import com.example.timetablesystem.entities.Batch;
import com.example.timetablesystem.entities.Course;
import com.example.timetablesystem.entities.Session;
import com.example.timetablesystem.entities.User;
import com.example.timetablesystem.service.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class RestUserController {
    @Autowired
    LoginService loginService;
    @Autowired
    UserService userService;
    @Autowired
    BatchService batchService;
    @Autowired
    SessionService sessionService;
    @Autowired
    CourseService courseService;
    @Autowired
    private BCryptPasswordEncoder encoder;


    @PostMapping("/login")
    public String login(@RequestBody RestLogin restLogin)
    {
        try{
            String isExist=loginService.login(restLogin);
            return isExist;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }


    @GetMapping("/getUser/{userId}")
    public List<User> getUser(@PathVariable(value = "userId")int userId)
    {
        List<User> users=new ArrayList<>();
        users.add(userService.findUserById(userId));
        return users;
    }


    @GetMapping("/getAllBatches")
    public List<Batch> getBatches()
    {
        List<Batch> batchList=batchService.getAllBatches();
        if(!batchList.isEmpty()){
            return batchList;
        }
        return null;
    }


    @GetMapping("/getAllCourses")
    public List<Course> getCourses()
    {
        List<Course> courseList=courseService.getAllCourses();
        if(!courseList.isEmpty()){
            return courseList;
        }
        return null;
    }

    @PostMapping("/updateUser")
    public String updateUser(@RequestBody User user){
        boolean isUpdated=false;
        User u=userService.findUserById(user.getUserId());
        u.setName(user.getName());
        u.setPhone(user.getPhone());
        if(user.getPassword() != null){
            u.setPassword(encoder.encode(user.getPassword()));
        }
        isUpdated=userService.updateUser(u);
        if(isUpdated){
            return "updated";
        }
        return "fail";
    }
}
