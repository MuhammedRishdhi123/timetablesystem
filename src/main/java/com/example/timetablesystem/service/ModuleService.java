package com.example.timetablesystem.service;

import com.example.timetablesystem.dto.ModuleDTO;
import com.example.timetablesystem.entities.Lecturer;
import com.example.timetablesystem.entities.Module;

import java.util.List;

public interface ModuleService {
    public Module saveModule(ModuleDTO moduleDTO);
    public List<Module> getAllModules();
    public void deleteModule(Module module);
    public Module getModuleById(int moduleId);
    public boolean updateModule(Module module);
    public void addLecturerToModule(int id, Lecturer lecturer);
    public boolean removeModuleLecturers(int id);
    public Module getModuleByTitle(String title);
}
