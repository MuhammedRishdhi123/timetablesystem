package com.example.timetablesystem.service;

import com.example.timetablesystem.dto.LecturerDTO;
import com.example.timetablesystem.entities.Lecturer;
import com.example.timetablesystem.entities.Role;
import com.example.timetablesystem.entities.Session;
import com.example.timetablesystem.entities.User;
import com.example.timetablesystem.repository.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class LecturerServiceImpl implements LecturerService {
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private LecturerRepository lecturerRepository;


    @Override
    public Lecturer saveLecturer(LecturerDTO lecturerDTO) {
        Lecturer lecturer=new Lecturer();
        User user=new User();
        user.setName(lecturerDTO.getLecturerName());
        user.setPassword(encoder.encode(lecturerDTO.getLecturerPassword()));
        user.setEmail(lecturerDTO.getLecturerEmail());
        user.setPhone(lecturerDTO.getLecturerPhone());
        user.setRoles(Stream.of(new Role("Lecturer")).collect(Collectors.toSet()));

        lecturer.setUser(user);
        return lecturerRepository.save(lecturer);
    }

    @Override
    public List<Lecturer> getAllLecturer() {
        return lecturerRepository.findAll();
    }

    @Override
    public Lecturer findByUserId(int userId) {
        return lecturerRepository.findByUserId(userId);
    }

    @Override
    public Lecturer findByLecturerId(int lecturerId) {
        return lecturerRepository.findByLecturerId(lecturerId);
    }

    @Override
    public void delete(Lecturer lecturer) {
        lecturerRepository.delete(lecturer);
    }

    @Override
    public boolean checkLecturerAvailability(Session session) {
        List<Session> sessionList= session.getLecturer().getSessions().stream().collect(Collectors.toList());
        for(Session s:sessionList){
            if(s.getDay()==session.getDay() && s.getLectureTime()==session.getLectureTime()) return false;
        }
        return true;
    }

    @Override
    public Lecturer getLecturerByName(String name) {
        return lecturerRepository.findByUserName(name);
    }
}
