package com.example.timetablesystem.RestController;

import com.example.timetablesystem.dto.FacultyDTO;
import com.example.timetablesystem.dto.SessionDTO;
import com.example.timetablesystem.dto.StudentDTO;
import com.example.timetablesystem.entities.*;
import com.example.timetablesystem.service.SessionService;
import com.example.timetablesystem.service.StudentService;
import com.example.timetablesystem.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/student")
@AllArgsConstructor
public class RestStudentController {
    @Autowired
    SessionService sessionService;

    @Autowired
    UserService userService;

    @Autowired
    StudentService studentService;




   @GetMapping("/getMyModules/{userId}")
    public List<Module> getMyModules(@PathVariable(value = "userId")int userId){
       User temp=userService.findUserById(userId);
       if(temp != null){
           List<Module> moduleList=temp.getStudent().getCourse().getModules().stream().collect(Collectors.toList());
           return moduleList;
       }
       return null;
   }


    @GetMapping("/getMyFaculty/{userId}")
    public List<FacultyDTO> getMyFaculty(@PathVariable(value = "userId")int userId){
        User temp=userService.findUserById(userId);
        List<FacultyDTO> facultyList=new ArrayList<>();
        if(temp != null){
            List<Module> modules=temp.getStudent().getCourse().getModules().stream().collect(Collectors.toList());
            for(Module m:modules){
                for(Lecturer l:m.getLecturers()){
                    FacultyDTO facultyDTO=new FacultyDTO();
                    facultyDTO.setName(l.getUser().getName());
                    facultyDTO.setEmail(l.getUser().getEmail());
                    facultyDTO.setContactNumber(l.getUser().getPhone());
                    facultyList.add(facultyDTO);
                }
            }
            return facultyList;
        }
        return null;
    }

    @GetMapping("/getStudent/{userId}")
    public List<StudentDTO> getStudent(@PathVariable(value = "userId")int userId)
    {
        List<StudentDTO> students=new ArrayList<>();
        User user=userService.findUserById(userId);
        if(user != null){
            Student student=user.getStudent();
            StudentDTO studentDTO=new StudentDTO();
            studentDTO.setStudentId(student.getStudentId());
            studentDTO.setStudentName(student.getUser().getName());
            studentDTO.setStudentEmail(student.getUser().getEmail());
            studentDTO.setStudentPhone(student.getUser().getPhone());
            studentDTO.setBatchTitle(student.getBatch().getBatchTitle());
            studentDTO.setCourseName(student.getCourse().getCourseTitle());
            students.add(studentDTO);
            return students;

        }

        return null;
    }


    @GetMapping("/getTimetable/{userId}")
    public List<SessionDTO> getTimetable(@PathVariable(value = "userId") int userId)
    {
        User temp=userService.findUserById(userId);
        if(temp.getStudent() != null){
            List<Session> sessionList=sessionService.getSessionsByBatchId(temp.getStudent().getBatch().getBatchId());
            List<SessionDTO> sessionDTOS=new ArrayList<>();
            for(Session s:sessionList){
                SessionDTO sessionDTO=new SessionDTO();
                sessionDTO.setSessionId(s.getSessionId());
                sessionDTO.setModuleTitle(s.getModule().getModuleTitle());
                sessionDTO.setBatchTitle(s.getBatch().getBatchTitle());
                sessionDTO.setLecturerName(s.getLecturer().getUser().getName());
                sessionDTO.setRoomName(s.getRoom().getRoomName());
                sessionDTO.setDay(s.getDay().getDayName());
                sessionDTO.setLectureTime(s.getLectureTime().getLectureTimeName());
                sessionDTO.setLectureType(s.getLectureType().getLectureTypeName());
                sessionDTOS.add(sessionDTO);
            }
            return sessionDTOS;
        }
        return null;
    }



    @PostMapping("/saveStudent")
    public String saveStudent(@RequestBody StudentDTO studentDTO){
        boolean isExist=userService.checkEmail(studentDTO.getStudentEmail());
        if(!isExist){
            studentService.saveStudent(studentDTO);
            return "success";
        }
        return "fail";
    }
}
