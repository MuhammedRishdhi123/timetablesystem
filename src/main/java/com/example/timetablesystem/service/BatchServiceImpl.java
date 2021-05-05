package com.example.timetablesystem.service;

import com.example.timetablesystem.dto.BatchRegistration;
import com.example.timetablesystem.entities.Batch;
import com.example.timetablesystem.repository.BatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BatchServiceImpl implements BatchService{

    @Autowired
    private BatchRepository batchRepository;

    @Override
    public Batch saveBatch(BatchRegistration batch) {
        Batch newBatch=new Batch();
        newBatch.setBatchTitle(batch.getBatchTitle());
        batchRepository.save(newBatch);
        return newBatch;
    }

    @Override
    public List<Batch> getAllBatches() {
       return batchRepository.findAll();
    }

    @Override
    public void deleteBatch(Batch batch) {
        batchRepository.delete(batch);
    }

    @Override
    public Batch getBatchByTitle(String title) {
        return batchRepository.findByBatchTitle(title);
    }

    @Override
    public Batch getBatchById(int batchId) {
        return (Batch) batchRepository.getOne(batchId);
    }
}
