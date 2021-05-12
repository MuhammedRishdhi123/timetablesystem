package com.example.timetablesystem.service;

import com.example.timetablesystem.dto.CourseRegistration;
import com.example.timetablesystem.dto.ModuleRegistration;
import com.example.timetablesystem.entities.Course;
import com.example.timetablesystem.entities.Lecturer;
import com.example.timetablesystem.entities.Module;

import java.util.List;

public interface ModuleService {
    public Module saveModule(ModuleRegistration moduleRegistration);
    public List<Module> getAllModules();
    public void deleteModule(Module module);
    public Module getModuleById(int moduleId);
    public boolean updateModule(Module module);
    public void addLecturerToModule(int id, Lecturer lecturer);
    public boolean removeModuleLecturers(int id);
}
