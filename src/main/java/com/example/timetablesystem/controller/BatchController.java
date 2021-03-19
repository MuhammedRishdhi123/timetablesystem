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

    @GetMapping("/addBatch")
    public String getAddBatchPage()
    {
        return "addBatch";
    }

    @PostMapping("/saveBatch")
    public String saveBatch(@ModelAttribute("batch") BatchRegistration batch)
    {
        batchService.saveBatch(batch);
        return "redirect:/batch/allbatches?success";
    }
    @GetMapping("/allbatches")
    public String getAllBatches(Model model)
    {
        List<Batch> batchList=batchService.getAllBatches();
        model.addAttribute("batches",batchList);
        return "manageBatch";
    }

    @GetMapping("/deleteBatch/{id}")
    public String deleteBatch(@PathVariable(value = "id")int id, Model model)
    {
        batchService.deleteBatch(batchService.getBatchById(id));
        return "redirect:/batch/allbatches?deleted";
    }
}
