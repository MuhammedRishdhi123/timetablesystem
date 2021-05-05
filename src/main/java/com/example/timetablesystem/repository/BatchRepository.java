package com.example.timetablesystem.repository;

import com.example.timetablesystem.entities.Admin;
import com.example.timetablesystem.entities.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchRepository extends JpaRepository<Batch,Integer> {
    @Query("SELECT batch from Batch batch where batch.batchTitle= ?1")
    Batch findByBatchTitle(String title);
}
