package com.example.timetablesystem.controller;

import com.example.timetablesystem.dto.BatchRegistration;
import com.example.timetablesystem.dto.CourseRegistration;
import com.example.timetablesystem.entities.Batch;
import com.example.timetablesystem.entities.Course;
import com.example.timetablesystem.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/batch")
public class BatchController {
    @Autowired
    private BatchService batchService;







}
