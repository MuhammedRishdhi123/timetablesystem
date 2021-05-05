package com.example.timetablesystem.controller;

import com.example.timetablesystem.dto.*;
import com.example.timetablesystem.entities.*;
import com.example.timetablesystem.entities.enums.Day;
import com.example.timetablesystem.entities.enums.LectureTime;
import com.example.timetablesystem.entities.enums.LectureType;
import com.example.timetablesystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
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


    @GetMapping("/home")
    public String loadHome(Model model)
    {
        model.addAttribute("students", studentService.getAllStudents().size());
        model.addAttribute("lecturers",lecturerService.getAllLecturer().size());
        model.addAttribute("batches",batchService.getAllBatches().size());
        model.addAttribute("courses",courseService.getAllCourses().size());
        return "AdminHome";
    }


    @GetMapping("/manageStudents")
    public String manageStudents(Model model)
    {
        List<Student> studentsList=studentService.getAllStudents();
        model.addAttribute("students",studentsList);
        return "manageStudents";

    }

    @GetMapping("/manageCourses")
    public String manageCourse(Model model)
    {
        List<Course> courseList=courseService.getAllCourses();
        model.addAttribute("courses",courseList);
        return "manageCourse";

    }

    @GetMapping("/manageRooms")
    public String manageRooms(Model model)
    {
        List<Room> roomList=roomService.getAllRooms();
        model.addAttribute("rooms",roomList);
        return "manageRoom";

    }

    @GetMapping("/manageBatches")
    public String manageBatches(Model model)
    {
        List<Batch> batchList= batchService.getAllBatches();
        model.addAttribute("batches",batchList);
        return "manageBatch";
    }

    @GetMapping("/manageLecturers")
    public String manageLecturers(Model model)
    {
        List<Lecturer> lecturerList= lecturerService.getAllLecturer();
        model.addAttribute("lecturers",lecturerList);
        return "manageLecturers";
    }

    @GetMapping("/manageModules")
    public String manageModules(Model model)
    {
        List<Module> moduleList= moduleService.getAllModules();
        LecturersList lecturersList=new LecturersList();
        List<Lecturer> lecturers=lecturerService.getAllLecturer();
        model.addAttribute("modules",moduleList);
        model.addAttribute("lecturersList",lecturersList);
        model.addAttribute("lecturers",lecturers);
        return "manageModules";
    }

    @GetMapping("/manageTimetable")
    public String manageTimetable(Model model)
    {
        List<Batch> batchList = batchService.getAllBatches();
        Session searchSession=new Session();
        model.addAttribute("sessionBatches",batchList);
        model.addAttribute("searchSession",searchSession);
        return "manageTimetable";
    }


    @GetMapping("/searchTimetable")
    public String searchTimetable(@RequestParam(name="batchId")int batchId,Model model)
    {
        List<Session> sessionList= sessionService.getSessionsByBatchId(batchId);
        Collections.sort(sessionList, new Comparator<Session>() {
            @Override
            public int compare(Session o1, Session o2) {
                if (o1.getDay() == o2.getDay()) {
                    return o1.getLectureTime().compareTo(o2.getLectureTime());
                } else {
                    return o1.getDay().compareTo(o2.getDay());
                }
            }
        });
        List<Batch> batchList= batchService.getAllBatches();
        if(sessionList.size() != 0)
        {
            model.addAttribute("sessions",sessionList);
            model.addAttribute("sessionBatches",batchList);
            model.addAttribute("sessionBatchTitle",batchService.getBatchById(batchId).getBatchTitle());
            return "manageTimetable";
        }
        return "redirect:/admin/manageTimetable?noTimetablesFound";

    }



    @GetMapping("/addCourse")
    public String getAddCoursePage()
    {
        return "addCourse";
    }

    @GetMapping("/addBatch")
    public String getAddBatchPage()
    {
        return "addBatch";
    }

    @GetMapping("/addLecturer")
    public String getAddLecturerPage()
    {
        return "registerLecturer";
    }

    @GetMapping("/addModule")
    public String getAddModulePage(Model model)
    {
        model.addAttribute("courses",courseService.getAllCourses());
        return "addModule";
    }

    @GetMapping("/addRoom")
    public String getAddRoomPage(Model model)
    {
        return "addRoom";
    }

    @GetMapping("/addSession")
    public String getAddSessionPage(Model model)
    {
        Session session=new Session();
        model.addAttribute("session",session);
        model.addAttribute("modules",moduleService.getAllModules());
        model.addAttribute("lecturers",lecturerService.getAllLecturer());
        model.addAttribute("daysOfWeek", Day.values());
        model.addAttribute("lectureTimes", LectureTime.values());
        model.addAttribute("lectureTypes", LectureType.values());
        model.addAttribute("batches",batchService.getAllBatches());
        model.addAttribute("rooms",roomService.getAllRooms());
        return "addSession";
    }

    @GetMapping("/deleteStudent/{userId}")
    public String deleteStudent(@PathVariable(value ="userId") int userId)
    {
        User user =userService.findUserById(userId);
        if(user.getStudent() != null)
        {
            studentService.delete(user.getStudent());
        }
        return "redirect:/admin/manageStudents?deleted";
    }

    @GetMapping("/deleteCourse/{courseId}")
    public String deleteCourse(@PathVariable(value ="courseId") int courseId)
    {
       Course course=courseService.getCourseById(courseId);
       if(course != null)
       {
           courseService.deleteCourse(course);
       }
        return "redirect:/admin/manageCourse?deleted";
    }

    @GetMapping("/deleteRoom/{roomId}")
    public String deleteRoom(@PathVariable(value ="roomId") int roomId)
    {
        Room room=roomService.getRoomById(roomId);
        if(room != null)
        {
            roomService.deleteRoom(room);
        }
        return "redirect:/admin/manageRooms?deleted";
    }

    @GetMapping("/deleteBatch/{id}")
    public String deleteBatch(@PathVariable(value = "id")int id, Model model)
    {
        batchService.deleteBatch(batchService.getBatchById(id));
        return "redirect:/admin/manageBatches?deleted";
    }


    @GetMapping("/deleteLecturer/{userId}")
    public String deleteLecturer(@PathVariable(value ="userId") int userId)
    {
        User user=userService.findUserById(userId);
        if(user.getLecturer() != null)
        {
            lecturerService.delete(user.getLecturer());
        }
        return "redirect:/admin/manageLecturers?deleted";
    }

    @GetMapping("/deleteModule/{moduleId}")
    public String deleteModule(@PathVariable(value ="moduleId") int moduleId)
    {
        Module module=moduleService.getModuleById(moduleId);
        if(module != null)
        {
            moduleService.deleteModule(module);
        }
        return "redirect:/admin/manageModules?deleted";
    }

    @GetMapping("/deleteSession/{sessionId}")
    public String deleteSession(@PathVariable(value ="sessionId") int sessionId)
    {
        Session session=sessionService.getSessionById(sessionId);
        if(session != null)
        {
            sessionService.deleteSession(session);
        }
        return "redirect:/admin/manageTimetable?deleted";
    }

    @GetMapping("/editStudent/{userId}")
    public String editStudent(@PathVariable(value = "userId")int userId,Model model)
    {
        User user =userService.findUserById(userId);
        model.addAttribute("user",user);
        return "viewStudent";
    }

    @GetMapping("/editCourse/{courseId}")
    public String editCourse(@PathVariable(value = "courseId")int courseId,Model model)
    {
        Course course =courseService.getCourseById(courseId);
        model.addAttribute("course",course);
        return "viewCourse";
    }

    @GetMapping("/editModule/{moduleId}")
    public String editModule(@PathVariable(value = "moduleId")int moduleId,Model model)
    {
        Module module =moduleService.getModuleById(moduleId);
        List<Course> courses=courseService.getAllCourses();
        courses.remove(module.getCourse());
        model.addAttribute("courses",courses);
        model.addAttribute("module",module);
        return "viewModule";
    }

    @GetMapping("/editLecturer/{userId}")
    public String editLecturer(@PathVariable(value = "userId")int userId,Model model)
    {
        User user =userService.findUserById(userId);
        model.addAttribute("user",user);
        return "viewLecturer";
    }

    @GetMapping("/editRoom/{roomId}")
    public String editRoom(@PathVariable(value = "roomId")int roomId,Model model)
    {
        Room room =roomService.getRoomById(roomId);
        model.addAttribute("room",room);
        return "viewRoom";
    }

    @PostMapping("/updateStudent")
    public String updateStudent(@ModelAttribute("User")User user)
    {

        if(userService.findUserById(user.getUserId()).getStudent() != null)
        {
            User newUser=userService.findUserById(user.getUserId());
            newUser.setName(user.getName());
            newUser.setPhone(user.getPhone());
            boolean saved=userService.updateUser(newUser);
            if(saved)
            {
                return "redirect:/admin/editStudent/"+newUser.getUserId()+"?updated";
            }
            else
            {
                return "redirect:/admin/editStudent/"+newUser.getUserId()+"?failed";
            }
        }
        else
        {
            return "redirect:/admin/editStudent/"+user.getUserId()+"?failed";
        }
    }


    @PostMapping("/updateLecturer")
    public String updateLecturer(@ModelAttribute("User")User user)
    {

        if(userService.findUserById(user.getUserId()).getLecturer() != null)
        {
            User newUser=userService.findUserById(user.getUserId());
            newUser.setName(user.getName());
            newUser.setPhone(user.getPhone());
            boolean saved=userService.updateUser(newUser);
            if(saved)
            {
                return "redirect:/admin/editLecturer/"+newUser.getUserId()+"?updated";
            }
            else
            {
                return "redirect:/admin/editLecturer/"+newUser.getUserId()+"?failed";
            }
        }
        else
        {
            return "redirect:/admin/editLecturer/"+user.getUserId()+"?failed";
        }
    }




    @PostMapping("/updateCourse")
    public String updateCourse(@ModelAttribute("course")Course course)
    {
        Course newCourse=courseService.getCourseById(course.getCourseId());
        if(newCourse != null)
        {
            newCourse.setCourseTitle(course.getCourseTitle());
            newCourse.setCourseDuration(course.getCourseDuration());
            boolean saved=courseService.updateCourse(newCourse);
            if(saved)
            {
                return "redirect:/admin/editCourse/"+newCourse.getCourseId()+"?updated";
            }
            else
            {
                return "redirect:/admin/editCourse/"+newCourse.getCourseId()+"?failed";
            }
        }
        return "redirect:/admin/editCourse/"+newCourse.getCourseId()+"?failed";
    }


    @PostMapping("/updateRoom")
    public String updateRoom(@ModelAttribute("room")Room room)
    {
        Room newRoom=roomService.getRoomById(room.getRoomId());
        if(newRoom != null)
        {
            newRoom.setRoomName(room.getRoomName());
            newRoom.setSeatingCapacity(room.getSeatingCapacity());
            newRoom.setStatus(room.getStatus());
            boolean saved=roomService.updateRoom(newRoom);
            if(saved)
            {
                return "redirect:/admin/editRoom/"+newRoom.getRoomId()+"?updated";
            }
            else
            {
                return "redirect:/admin/editRoom/"+newRoom.getRoomId()+"?failed";
            }
        }
        return "redirect:/admin/editRoom/"+newRoom.getRoomId()+"?failed";
    }


    @PostMapping("/updateModule")
    public String updateModule(@ModelAttribute("Module")Module module)
    {

        if(moduleService.getModuleById(module.getModuleId()) != null)
        {
            Module newModule=moduleService.getModuleById(module.getModuleId());
            newModule.setModuleTitle(module.getModuleTitle());
            newModule.setModuleCredits(module.getModuleCredits());
            newModule.setCourse(module.getCourse());
            boolean saved=moduleService.updateModule(newModule);
            if(saved)
            {
                return "redirect:/admin/editModule/"+module.getModuleId()+"?updated";
            }
            else
            {
                return "redirect:/admin/editModule/"+module.getModuleId()+"?failed";
            }
        }
        else
        {
            return "redirect:/admin/editModule/"+module.getModuleId()+"?failed";
        }
    }



    @PostMapping("/saveCourse")
    public String saveCourse(@ModelAttribute("course") CourseRegistration course)
    {
        courseService.saveCourse(course);
        return "redirect:/admin/manageCourses?success";
    }

    @PostMapping("/saveRoom")
    public String saveRoom(@ModelAttribute("room") RoomRegistration room)
    {
        roomService.saveRoom(room);
        return "redirect:/admin/manageRooms?success";
    }

    @PostMapping("/saveBatch")
    public String saveBatch(@ModelAttribute("batch") BatchRegistration batch)
    {
        batchService.saveBatch(batch);
        return "redirect:/admin/manageBatches?success";
    }

    @PostMapping("/saveModule")
    public String saveModule(@ModelAttribute("Module") ModuleRegistration moduleRegistration)
    {
        moduleService.saveModule(moduleRegistration);
        return "redirect:/admin/manageModules?success";
    }

    @PostMapping("/saveSession")
    public String saveSession(@ModelAttribute("session") Session session)
    {
        boolean isLecturerFree=lecturerService.checkLecturerAvailability(session);
        boolean isSessionFree=sessionService.checkSessionAvailability(session);
        boolean isRoomFree=roomService.checkRoomAvailability(session);
        if(isLecturerFree && isRoomFree && isSessionFree) {
            sessionService.saveSession(session);
            return "redirect:/admin/manageTimetable?success";
        }
        else{
            if(!isLecturerFree) return "redirect:/admin/manageTimetable?failedLecturer";
            if(!isSessionFree) return "redirect:/admin/manageTimetable?failedSession";
            if(!isRoomFree) return "redirect:/admin/manageTimetable?failedRoom";
        }
        return null;
    }

    @PostMapping("/saveLecturer")
    public String saveLecturer(@ModelAttribute("lecturer") LecturerRegistration lecturerRegistration)
    {
        boolean emailExist=userService.checkEmail(lecturerRegistration.getEmail());
        if(emailExist)
        {
            return "redirect:/admin/addLecturer?invalidEmail";
        }
        else if(!emailExist){
            lecturerService.saveLecturer(lecturerRegistration);
            return "redirect:/admin/manageLecturers?success";
        }
        return null;

    }

    @GetMapping("/addAdmin")
    public String registerAdminPage()
    {
        return "registerAdmin";
    }

    @PostMapping("/saveAdmin")
    public String saveAdmin(@ModelAttribute("Admin")AdminRegistration adminRegistration)
    {
        boolean emailExist=userService.checkEmail(adminRegistration.getEmail());
        if(emailExist)
        {
            return "redirect:/admin/addAdmin?invalidEmail";
        }
        else if(!emailExist){
            adminService.saveAdmin(adminRegistration);
            return "redirect:/admin/addAdmin?success";
        }
        return null;
    }

    @PostMapping("/addModuleLecturer")
    public String addModuleLecturer(@RequestParam("id")int id,@ModelAttribute("LecturersList") LecturersList lecturersList)
    {
        try{
            List<Lecturer> lecturers=lecturersList.getLecturerList();
            for (Lecturer lecturer:lecturers)
            {
                moduleService.addLecturerToModule(id,lecturer);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:manageModules?updated";
    }

    @PostMapping("/removeModuleLecturers")
    public String removeModuleLecturers(@RequestParam("id")int id)
    {
        boolean result=moduleService.removeModuleLecturers(id);
        if(result){
            return "redirect:manageModules?updated";
        }
        return "redirect:manageModules?updatefailed";
    }


    @PostMapping("/updateuser")
    public String updateUser(@ModelAttribute("user")User user)
    {
        boolean isUpdated=false;
        User loggedUser=userService.findUserById(user.getUserId());
        loggedUser.setName(user.getName());
        loggedUser.setPhone(user.getPhone());
        if(user.getPassword() != null)
        {
            loggedUser.setPassword(encoder.encode(user.getPassword()));
        }
        isUpdated=userService.updateUser(loggedUser);
        if(isUpdated)
        {
            return "redirect:/admin/profile?updated";
        }
        return "redirect:/admin/profile?failed";
    }

    @GetMapping("/profile")
    public String getProfile(Model model)
    {
        User loggedUser=userService.loggedUser();

        if(loggedUser != null)
        {
            model.addAttribute("user",loggedUser);
        }
        return "adminProfile";
    }



}
