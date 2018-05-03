package org.dt.ncepu.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dt.ncepu.domain.Grade;
import org.dt.ncepu.domain.NewStudent;
import org.dt.ncepu.service.NewStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/09/25 0025.
 */
@Controller
public class NewStudentController {
    private static final Log loggers =
            LogFactory.getLog(NewStudentController.class);


    @Autowired
    private NewStudentService newStudentService;

    @RequestMapping(value = "/showNewStudents/", method = {RequestMethod.GET, RequestMethod.POST})
    public String showNewStudents(Model model) {
        model.addAttribute("newStudents", newStudentService.showNewStudents());
        return "NewStudentList";
    }

    @RequestMapping(value = "/deleteNewStudent/{student}", method = {RequestMethod.GET, RequestMethod.POST})
    public String deleteNewStudent(@PathVariable String student) {
        newStudentService.deleteNewStudent(student);
        return "redirect:/showNewStudents";
    }

    @RequestMapping(value = "/readNewStudent/{student}", method = {RequestMethod.GET, RequestMethod.POST})
    public String readNewStudent(@PathVariable String student) {
        newStudentService.readNewStudent(student);
        return "redirect:/showNewStudents/";
    }

    @RequestMapping(value = "/addNewStudent", method = {RequestMethod.POST}, consumes = "application/json")
    @ResponseBody
    public boolean addNewStudent(@RequestBody NewStudent newStudent) {
        try{
            newStudentService.addNewStudent(newStudent);
            loggers.info(newStudent.getName()+newStudent.getId());
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @RequestMapping(value = "/json1", method = {RequestMethod.POST}, consumes = "application/json")
    @ResponseBody
    public Grade testJson1(@RequestBody Grade grade) {
        loggers.info("json1");
        loggers.info(grade.getYear());
        return grade;
    }
}
