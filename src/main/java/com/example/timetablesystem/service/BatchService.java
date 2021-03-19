package com.example.timetablesystem.service;

import com.example.timetablesystem.dto.BatchRegistration;
import com.example.timetablesystem.dto.CourseRegistration;
import com.example.timetablesystem.entities.Batch;
import com.example.timetablesystem.entities.Course;

import java.util.List;

public interface BatchService {
    public Batch saveBatch(BatchRegistration batch);
    public List<Batch> getAllBatches();
    public void deleteBatch(Batch batch);
    public Batch getBatchById(int batchId);
}
