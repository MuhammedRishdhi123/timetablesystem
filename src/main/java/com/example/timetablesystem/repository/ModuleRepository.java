package com.example.timetablesystem.repository;

import com.example.timetablesystem.entities.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends JpaRepository<Module,Integer> {
}
