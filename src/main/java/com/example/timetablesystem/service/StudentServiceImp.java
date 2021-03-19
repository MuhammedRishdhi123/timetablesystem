package com.example.timetablesystem.service;

import com.example.timetablesystem.dto.StudentRegistration;
import com.example.timetablesystem.entities.Role;
import com.example.timetablesystem.entities.Student;
import com.example.timetablesystem.entities.User;
import com.example.timetablesystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StudentServiceImp implements StudentService{
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public Student saveStudent(StudentRegistration studentRegistration) {
        Student student=new Student();
        User user=new User();

        user.setEmail(studentRegistration.getEmail());
        user.setName(studentRegistration.getName());
        user.setPhone(studentRegistration.getPhone());
        user.setPassword(encoder.encode(studentRegistration.getPassword()));
        user.setRoles(Stream.of(new Role("Student")).collect(Collectors.toSet()));

        student.setUser(user);
        student.setCourse(studentRegistration.getCourse());
        student.setBatch(studentRegistration.getBatch());
        user.setStudent(student);
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student findByUserId(int userId) {
        return studentRepository.findByUserId(userId);
    }

    @Override
    public void delete(Student student) {
        studentRepository.delete(student);
    }

}
