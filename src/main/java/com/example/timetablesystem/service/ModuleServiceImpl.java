package com.example.timetablesystem.service;

import com.example.timetablesystem.dto.ModuleDTO;
import com.example.timetablesystem.entities.Lecturer;
import com.example.timetablesystem.entities.Module;
import com.example.timetablesystem.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ModuleServiceImpl implements ModuleService{
    @Autowired
    private ModuleRepository moduleRepository;
    @Override
    public Module saveModule(ModuleDTO moduleDTO) {
        Module module=new Module();
        module.setModuleCredits(moduleDTO.getModuleCredits());
        module.setModuleTitle(moduleDTO.getModuleTitle());
        module.setCourse(moduleDTO.getCourse());
        moduleRepository.save(module);
        return module;
    }

    @Override
    public List<Module> getAllModules() {
        return moduleRepository.findAll();
    }

    @Override
    public void deleteModule(Module module) {
        moduleRepository.delete(module);
    }

    @Override
    public Module getModuleById(int moduleId) {
        return moduleRepository.getOne(moduleId);
    }

    @Override
    public boolean updateModule(Module module) {
        Module isExist=moduleRepository.getOne(module.getModuleId());
        if(isExist != null) {
            moduleRepository.save(module);
            return true;
        }
        return false;
    }

    @Override
    public void addLecturerToModule(int id, Lecturer lecturer) {
        Module module=moduleRepository.getOne(id);
        if(module != null){
            Set<Lecturer> tempLecturers= module.getLecturers();
            tempLecturers.add(lecturer);
            module.setLecturers(tempLecturers);
            moduleRepository.save(module);
        }
    }

    @Override
    public boolean removeModuleLecturers(int id) {
        Module module=moduleRepository.getOne(id);
        if(module != null){
            module.getLecturers().clear();
            moduleRepository.save(module);
            return true;
        }
        return false;
    }

    @Override
    public Module getModuleByTitle(String title) {
        return moduleRepository.findByModuleTitle(title);
    }
}
