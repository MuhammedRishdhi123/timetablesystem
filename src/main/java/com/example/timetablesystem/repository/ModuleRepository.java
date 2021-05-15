package com.example.timetablesystem.repository;

import com.example.timetablesystem.entities.Admin;
import com.example.timetablesystem.entities.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends JpaRepository<Module,Integer> {
    @Query("SELECT module from Module module where module.moduleTitle= ?1")
    Module findByModuleTitle(String title);
}
