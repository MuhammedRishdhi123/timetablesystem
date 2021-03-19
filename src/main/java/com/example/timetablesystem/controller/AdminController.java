package com.example.timetablesystem.controller;

import com.example.timetablesystem.entities.Batch;
import com.example.timetablesystem.entities.Course;
import com.example.timetablesystem.entities.Student;
import com.example.timetablesystem.entities.User;
import com.example.timetablesystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private LecturerService lecturerService;
    @Autowired
    private BatchService batchService;
    @Autowired
    private CourseService courseService;


    @GetMapping("/home")
    public String loadHome(Model model)
    {
        model.addAttribute("students", studentService.getAllStudents().size());
        model.addAttribute("lecturers",lecturerService.getAllLecturer().size());
        model.addAttribute("batches",batchService.getAllBatches().size());
        model.addAttribute("courses",courseService.getAllCourses().size());
        return "AdminHome";
    }


    @GetMapping("/manageStudents")
    public String manageStudents(Model model)
    {
        List<Student> studentsList=studentService.getAllStudents();
        model.addAttribute("students",studentsList);
        return "manageStudents";

    }

    @GetMapping("/manageCourse")
    public String manageCourse(Model model)
    {
        List<Course> courseList=courseService.getAllCourses();
        model.addAttribute("courses",courseList);
        return "manageCourse";

    }

    @GetMapping("/deleteStudent/{userId}")
    public String deleteStudent(@PathVariable(value ="userId") int userId)
    {
        User user =userService.findUserById(userId);
        if(user.getStudent() != null)
        {
            studentService.delete(user.getStudent());
        }
        return "redirect:/admin/manageStudents?deleted";
    }

    @GetMapping("/deleteCourse/{courseId}")
    public String deleteCourse(@PathVariable(value ="courseId") int courseId)
    {
       Course course=courseService.getCourseById(courseId);
       if(course != null)
       {
           courseService.deleteCourse(course);
       }
        return "redirect:/admin/manageCourse?deleted";
    }

    @GetMapping("/editStudent/{userId}")
    public String editStudent(@PathVariable(value = "userId")int userId,Model model)
    {
        User user =userService.findUserById(userId);
        model.addAttribute("user",user);
        return "viewStudent";
    }

    @GetMapping("/editCourse/{courseId}")
    public String editCourse(@PathVariable(value = "courseId")int courseId,Model model)
    {
        Course course =courseService.getCourseById(courseId);
        model.addAttribute("course",course);
        return "viewCourse";
    }

    @PostMapping("/updateStudent")
    public String updateStudent(@ModelAttribute("User")User user)
    {

        if(userService.findUserById(user.getUserId()).getStudent() != null)
        {
            User newUser=userService.findUserById(user.getUserId());
            newUser.setName(user.getName());
            newUser.setPhone(user.getPhone());
            boolean saved=userService.updateUser(newUser);
            if(saved)
            {
                return "redirect:/admin/editStudent/"+newUser.getUserId()+"?updated";
            }
            else
            {
                return "redirect:/admin/editStudent/"+newUser.getUserId()+"?failed";
            }
        }
        else
        {
            return "redirect:/admin/editStudent/"+user.getUserId()+"?failed";
        }
    }

}
