package com.example.timetablesystem.service;

import com.example.timetablesystem.dto.LecturerRegistration;
import com.example.timetablesystem.entities.Lecturer;
import com.example.timetablesystem.entities.Role;
import com.example.timetablesystem.entities.Session;
import com.example.timetablesystem.entities.User;
import com.example.timetablesystem.entities.enums.Day;
import com.example.timetablesystem.entities.enums.LectureTime;
import com.example.timetablesystem.repository.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
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
    public Lecturer saveLecturer(LecturerRegistration lecturerRegistration) {
        Lecturer lecturer=new Lecturer();
        User user=new User();
        user.setName(lecturerRegistration.getName());
        user.setPassword(encoder.encode(lecturerRegistration.getPassword()));
        user.setEmail(lecturerRegistration.getEmail());
        user.setPhone(lecturerRegistration.getPhone());
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
}
