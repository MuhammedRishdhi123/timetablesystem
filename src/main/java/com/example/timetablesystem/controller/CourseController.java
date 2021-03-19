package com.example.timetablesystem.controller;

import com.example.timetablesystem.dto.CourseRegistration;
import com.example.timetablesystem.entities.Course;
import com.example.timetablesystem.service.CourseService;
import com.example.timetablesystem.service.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseServiceImpl courseService;

    @GetMapping("/addCourse")
    public String getAddCoursePage()
    {
        return "addCourse";
    }

    @PostMapping("/saveCourse")
    public String saveCourse(@ModelAttribute("course") CourseRegistration course)
    {
        courseService.saveCourse(course);
        return "redirect:/course/allcourses?success";
    }

    @GetMapping("/allcourses")
    public String getAllCourse(Model model)
    {
        List<Course> courseList=courseService.getAllCourses();
        model.addAttribute("courses",courseList);
        return "manageCourse";
    }

    @GetMapping("/editCourse/{id}")
    public String editCourse(@PathVariable(value = "id")int id,Model model)
    {
        model.addAttribute("course",courseService.getCourseById(id));
        return "editCourse";
    }

    @GetMapping("/deleteCourse/{id}")
    public String deleteCourse(@PathVariable(value = "id")int id,Model model)
    {
        courseService.deleteCourse(courseService.getCourseById(id));
        return "redirect:/course/allcourses?deleted";
    }


}
