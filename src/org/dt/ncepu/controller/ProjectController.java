package org.dt.ncepu.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dt.ncepu.domain.Project;
import org.dt.ncepu.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/09/10 0010.
 */
@Controller
public class ProjectController {
    private static final Log loggers=
            LogFactory.getLog(ProjectController.class);

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/showProjects", method = {RequestMethod.GET, RequestMethod.POST})
    public String showProjects(Model model){
        List<Project> projects=projectService.showProjects();
        model.addAttribute("projects",projects);
        return "ProjectList";
    }

    @RequestMapping(value = "/addProject", method = {RequestMethod.GET, RequestMethod.POST})
    public String addProject(Model model) {
        model.addAttribute("project",new Project());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        model.addAttribute("time",sdf.format(new Date(System.currentTimeMillis())));
        return "/ProjectForm";
    }


    @RequestMapping(value = "/saveProject", method = {RequestMethod.GET, RequestMethod.POST})
    public String saveProject(Project project,Model model) {
        loggers.info("saveProject");
        if(project.setTime()){
            projectService.addProject(project);
            return "redirect:/showProjects";
        }
        model.addAttribute("Error","日期格式有误");
        return "/ProjectForm";
    }

    @RequestMapping(value = "/deleteProject/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public String deleteProject(@PathVariable int id) {
        projectService.deleteProject(id);
        return "redirect:/showProjects";
    }
}
