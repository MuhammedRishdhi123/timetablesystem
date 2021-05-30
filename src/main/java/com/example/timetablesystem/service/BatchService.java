package com.example.timetablesystem.service;

import com.example.timetablesystem.dto.BatchDTO;
import com.example.timetablesystem.entities.Batch;

import java.util.List;

public interface BatchService {
    public Batch saveBatch(BatchDTO batch);
    public List<Batch> getAllBatches();
    public void deleteBatch(Batch batch);
    public Batch getBatchByTitle(String title);
    public Batch getBatchById(int batchId);
}
