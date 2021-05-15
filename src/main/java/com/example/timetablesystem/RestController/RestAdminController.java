package com.example.timetablesystem.RestController;

import com.example.timetablesystem.dto.*;
import com.example.timetablesystem.entities.*;
import com.example.timetablesystem.entities.enums.Day;
import com.example.timetablesystem.entities.enums.LectureTime;
import com.example.timetablesystem.entities.enums.LectureType;
import com.example.timetablesystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class RestAdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private LecturerService lecturerService;
    @Autowired
    private BatchService batchService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    BCryptPasswordEncoder encoder;

    @GetMapping("/getSessions/{batchTitle}")
    public List<SessionDTO> getBatchSessions(@PathVariable(value = "batchTitle")String batchTitle)
    {
        List<SessionDTO> sessions=new ArrayList<>();
        for(Session session:sessionService.getSessionsByBatchTitle(batchTitle)){
            SessionDTO dto=new SessionDTO();
            dto.setSessionId(session.getSessionId());
            dto.setModuleTitle(session.getModule().getModuleTitle());
            dto.setBatchTitle(session.getBatch().getBatchTitle());
            dto.setLecturerName(session.getLecturer().getUser().getName());
            dto.setDay(session.getDay().getDayName());
            dto.setLectureType(session.getLectureType().getLectureTypeName());
            dto.setRoomName(session.getRoom().getRoomName());
            dto.setLectureTime(session.getLectureTime().getLectureTimeName());
            sessions.add(dto);
        }
        return sessions;
    }


    @GetMapping("/deleteSession/{sessionId}")
    public String deleteSession(@PathVariable(value="sessionId")int sessionId){
        Session session=sessionService.getSessionById(sessionId);
        if(session != null){
            sessionService.deleteSession(session);
            return "success";
        }
       return "failed";
    }

    @GetMapping("/deleteModule/{moduleId}")
    public String deleteModule(@PathVariable(value="moduleId")int moduleId){
        Module module=moduleService.getModuleById(moduleId);
        if(module != null){
            moduleService.deleteModule(module);
            return "success";
        }
        return "failed";
    }


    @GetMapping("/deleteStudent/{studentId}")
    public String deleteStudent(@PathVariable(value="studentId")int studentId){
        Student student=studentService.findByStudentId(studentId);
        if(student != null){
            userService.deleteUser(student.getUser().getUserId());
            studentService.delete(student);
            return "success";
        }
        return "failed";
    }

    @GetMapping("/deleteLecturer/{lecturerId}")
    public String deleteLecturer(@PathVariable(value="lecturerId")int lecturerId){
        Lecturer lecturer=lecturerService.findByLecturerId(lecturerId);
        if(lecturer != null){
            userService.deleteUser(lecturer.getUser().getUserId());
            lecturerService.delete(lecturer);
            return "success";
        }
        return "failed";
    }

    @GetMapping("/getAllRooms")
    public List<RoomDTO> getAllRooms(){
        List<Room> rooms=roomService.getAllRooms();
        List<RoomDTO> roomList=new ArrayList<>();
        for (Room r:rooms){
            RoomDTO roomDTO=new RoomDTO();
            roomDTO.setRoomId(r.getRoomId());
            roomDTO.setRoomName(r.getRoomName());
            roomDTO.setStatus(r.getStatus());
            roomDTO.setSeatingCapacity(r.getSeatingCapacity());
            roomList.add(roomDTO);
        }
        return roomList;
    }

    @GetMapping("/getAllLecturers")
    public List<LecturerDTO> getAllLecturers(){
        List<Lecturer> lecturers=lecturerService.getAllLecturer();
        List<LecturerDTO> lecturerList=new ArrayList<>();

        for(Lecturer l:lecturers){
            LecturerDTO lecturerDTO=new LecturerDTO();
            lecturerDTO.setLecturerId(l.getLecturerId());
            lecturerDTO.setLecturerName(l.getUser().getName());
            lecturerDTO.setLecturerEmail(l.getUser().getEmail());
            lecturerDTO.setLecturerPhone(l.getUser().getPhone());
            lecturerList.add(lecturerDTO);
        }
        return lecturerList;
    }



    @GetMapping("/getAllModules")
    public List<ModuleDTO> getAllModules(){
        List<Module> modules=moduleService.getAllModules();
        List<ModuleDTO> moduleList=new ArrayList<>();

        for(Module m:modules){
            ModuleDTO moduleDTO=new ModuleDTO();
            moduleDTO.setModuleId(m.getModuleId());
            moduleDTO.setModuleTitle(m.getModuleTitle());
            moduleDTO.setModuleCredits(m.getModuleCredits());
            moduleList.add(moduleDTO);
        }
        return moduleList;
    }

    @GetMapping("/getAllStudents")
    public List<StudentDTO> getAllStudents(){
        List<Student> students=studentService.getAllStudents();
        List<StudentDTO> studentList=new ArrayList<>();

        for(Student s:students){
            StudentDTO studentDTO=new StudentDTO();
            studentDTO.setStudentId(s.getStudentId());
            studentDTO.setStudentName(s.getUser().getName());
            studentDTO.setStudentEmail(s.getUser().getEmail());
            studentDTO.setStudentPhone(s.getUser().getPhone());
            studentDTO.setCourseName(s.getCourse().getCourseTitle());
            studentDTO.setBatchTitle(s.getBatch().getBatchTitle());

            studentList.add(studentDTO);
        }
        return studentList;
    }

    @PostMapping("/saveSession")
    public String saveSession(@RequestBody SessionDTO sessionDTO)
    {
        Session session=new Session();
        if(sessionDTO != null){
           session.setModule(moduleService.getModuleByTitle(sessionDTO.getModuleTitle()));
           session.setLecturer(lecturerService.getLecturerByName(sessionDTO.getLecturerName()));
           session.setBatch(batchService.getBatchByTitle(sessionDTO.getBatchTitle()));
           session.setRoom(roomService.getRoomByName(sessionDTO.getRoomName()));
           for(Day d:Day.values()) {
               if (d.getDayName().equalsIgnoreCase(sessionDTO.getDay())) {
                   session.setDay(d);
               }
           }
           for(LectureTime lt:LectureTime.values()){
               if(lt.getLectureTimeName().equalsIgnoreCase(sessionDTO.getLectureTime())){
                   session.setLectureTime(lt);
               }
           }
           for(LectureType type:LectureType.values()){
               if(type.getLectureTypeName().equalsIgnoreCase(sessionDTO.getLectureType())){
                   session.setLectureType(type);
               }
           }

            boolean isLecturerFree=lecturerService.checkLecturerAvailability(session);
            boolean isSessionFree=sessionService.checkSessionAvailability(session);
            boolean isRoomFree=roomService.checkRoomAvailability(session);
            if(isLecturerFree && isRoomFree && isSessionFree) {
                sessionService.saveSession(session);
                return "success";
            }
            else{
                if(!isLecturerFree) return "failLecturer";
                if(!isSessionFree) return "failSession";
                if(!isRoomFree) return "failRoom";
            }

        }

        return null;
    }


    @PostMapping("/updateModule")
    public String updateModule(@RequestBody ModuleDTO moduleDTO){
        boolean isUpdated=false;
        Module m=moduleService.getModuleById(moduleDTO.getModuleId());
        m.setModuleTitle(moduleDTO.getModuleTitle());
        m.setModuleCredits(moduleDTO.getModuleCredits());
        isUpdated=moduleService.updateModule(m);
        if(isUpdated){
            return "success";
        }
        return "fail";
    }

    @PostMapping("/updateStudent")
    public String updateStudent(@RequestBody StudentDTO studentDTO){
        boolean isUpdated=false;
        Student s=studentService.findByStudentId(studentDTO.getStudentId());
        User u=s.getUser();
        u.setName(studentDTO.getStudentName());
        u.setPhone(studentDTO.getStudentPhone());
        isUpdated=userService.updateUser(u);
        if(isUpdated){
            return "success";
        }
        return "fail";
    }


    @PostMapping("/updateLecturer")
    public String updateLecturer(@RequestBody LecturerDTO lecturerDTO){
        boolean isUpdated=false;
        Lecturer l=lecturerService.findByLecturerId(lecturerDTO.getLecturerId());
        User u=l.getUser();
        u.setName(lecturerDTO.getLecturerName());
        u.setPhone(lecturerDTO.getLecturerPhone());
        isUpdated=userService.updateUser(u);
        if(isUpdated){
            return "success";
        }
        return "fail";
    }
}
