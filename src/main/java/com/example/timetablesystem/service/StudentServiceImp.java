package com.example.timetablesystem.service;

import com.example.timetablesystem.dto.StudentDTO;
import com.example.timetablesystem.entities.Role;
import com.example.timetablesystem.entities.Student;
import com.example.timetablesystem.entities.User;
import com.example.timetablesystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import org.thymeleaf.context.Context;
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
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    CourseService courseService;
    @Autowired
    BatchService batchService;

    private String MESSAGE_SUBJECT=" ACCOUNT REGISTRATION SUCCESSFULL";

    @Override
    public Student saveStudent(StudentDTO studentDTO) {
        Student student=new Student();
        User user=new User();

        user.setEmail(studentDTO.getStudentEmail());
        user.setName(studentDTO.getStudentName());
        user.setPhone(studentDTO.getStudentPhone());
        user.setPassword(encoder.encode(studentDTO.getPassword()));
        user.setRoles(Stream.of(new Role("Student")).collect(Collectors.toSet()));

        student.setUser(user);
        student.setCourse(courseService.getCourseByTitle(studentDTO.getCourseName()));
        student.setBatch(batchService.getBatchByTitle(studentDTO.getBatchTitle()));
        user.setStudent(student);
        Context context=new Context();
        context.setVariable("message",user.getName());
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper=new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("noreply@protabler.com");
            messageHelper.setTo(user.getEmail());
            messageHelper.setSubject(MESSAGE_SUBJECT);
            messageHelper.setText(templateEngine.process("SuccessEmail",context));
        };
        try {
            mailSender.send(messagePreparator);
        }catch (MailException e){
            e.printStackTrace();
        }

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
    public Student findByStudentId(int studentId) {
        return studentRepository.findByStudentId(studentId);
    }

    @Override
    public void delete(Student student) {
        studentRepository.delete(student);
    }

}
