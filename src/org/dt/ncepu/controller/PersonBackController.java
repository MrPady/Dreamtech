package org.dt.ncepu.controller;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dt.ncepu.domain.Grade;
import org.dt.ncepu.domain.Login;
import org.dt.ncepu.domain.Person;
import org.dt.ncepu.domain.Recruit;
import org.dt.ncepu.form.PersonForm;
import org.dt.ncepu.service.PersonService;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class PersonBackController {
    private static final Log loggers =
            LogFactory.getLog(PersonBackController.class);

    public static String path=System.getProperty("pady.webapp")+"\\src\\pic\\people";

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/addPerson", method = {RequestMethod.GET, RequestMethod.POST})
    public String addPerson() {
        loggers.info("addCalled");
        return "PersonForm";
    }

    @RequestMapping(value = "/savePerson", method = {RequestMethod.GET, RequestMethod.POST})
    public String savePerson(@RequestParam("file") MultipartFile file, PersonForm personForm, RedirectAttributes redirectAttributes) {
        Person person = Person.PersonForm2Person(personForm);
        personService.addPerson(person);
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path,personForm.getId()+".jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/showPeople/" + 2000;
    }

    @RequestMapping(value = "/showPerson/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public String showPerson(@PathVariable String id, Model model) {
        Person person = personService.getPersonDetailsFromId(id, true);
        model.addAttribute("person", person);
        return "/PersonDetails";
    }

    @RequestMapping(value = "/showPeople/{grade}", method = {RequestMethod.GET, RequestMethod.POST})
    public String showPeople(Model model, @PathVariable int grade) {
        List<Person> people = personService.getPeopleFromGrade(grade, true);
        model.addAttribute("years", personService.getGrades());
        model.addAttribute("people", people);
        return "PeopleList";
    }

    @RequestMapping(value = "/addGrade", method = {RequestMethod.POST, RequestMethod.GET})
    public String addGrade() {
        Grade.AddMaxGrade();
        return "redirect:/showPeople/" + 2000;
    }

    @RequestMapping(value = "/deletePerson/{id}/{Grade}", method = {RequestMethod.GET, RequestMethod.POST})
    public String deletePerson(@PathVariable String id, @PathVariable String Grade) {
        int i = Integer.parseInt(Grade);
        personService.deletePersonFrom(id);
        return "redirect:/showPeople/" + i;
    }

    @RequestMapping(value = "/saveEditedPerson", method = {RequestMethod.GET, RequestMethod.POST})
    public void saveEditPerson(@RequestParam("file") MultipartFile file,Person person,HttpServletResponse response) {
        personService.editPerson(person);
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path,person.getId()+".jpg"));
            personService.changPic(person.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            response.sendRedirect("http://localhost:8080/dt2017/index.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/editPerson", method = {RequestMethod.GET, RequestMethod.POST})
    public String editPerson(Model model,HttpSession session){
        Login login= (Login) session.getAttribute("currentUser");
        //Login login=(Login)model.asMap().get("userInfo");
        if(personService.checkPassword(login).equals("1")){
            model.addAttribute("person",personService.getPersonDetailsFromId(login.getName(),true));
            return "PersonDetails";
        }else {
            return "Error";
        }
    }

    @RequestMapping(value = "/editPerson/{secret}", method = {RequestMethod.GET, RequestMethod.POST})
    public String editPerson(Model model,@PathVariable int secret){
        if(secret==115878){
            model.addAttribute("person",new Person());
        }
        return "PersonDetails";
    }

    @RequestMapping(value = "/mainController", method = {RequestMethod.GET, RequestMethod.POST})
    public String editRecruit(Model model) {
        boolean isRecruiting= personService.isRecruiting();
        model.addAttribute("recruit",new Recruit(isRecruiting));
        return "ControllerForm";
    }

    @RequestMapping(value = "/saveController", method = {RequestMethod.GET, RequestMethod.POST})
    public String saveRecruit(Recruit recruit) {
        personService.changeRecruit(recruit.isRecruiting());
        return "redirect:/mainController";
    }

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public int login(@RequestBody Login login, Model model, HttpSession session) {
        loggers.info(login.getName()+" Login");
        String res=personService.checkPassword(login);
        session.setAttribute("currentUser",login);
        //model.addAttribute("userInfo",login);
        return Integer.parseInt(res);
    }

    @RequestMapping(value = "/json/personList/{grade}",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public List<Person> getPeopleInJsonWithDetails(@PathVariable int grade,Model model,HttpSession session){
        Login login= (Login) session.getAttribute("currentUser");
        //Login login=(Login)model.asMap().get("userInfo");
        if(personService.checkPassword(login).equals("1")){
            loggers.info("personList:true");
            return personService.getPeopleFromGrade(grade,true);
        }
        loggers.info("personList:false");
        return  personService.getPeopleFromGrade(grade,false);
    }

    @RequestMapping(value = "/json/personDetail/{id}",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Person getPersonInJsonWithDetails(@PathVariable int id,Model model,HttpSession session){
        Login login= (Login) session.getAttribute("currentUser");
        //Login login=(Login)model.asMap().get("userInfo");
        if(personService.checkPassword(login).equals("1")){
            loggers.info("personList:true");
            return personService.getPersonDetailsFromId(String.valueOf(id),true);
        }
        loggers.info("personList:false");
        return personService.getPersonDetailsFromId(String.valueOf(id),false);
    }

    @RequestMapping(value = "/json/hasLogined",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public boolean hasLogined(Model model,HttpSession session){
        Login login= (Login) session.getAttribute("currentUser");
        if(personService.checkPassword(login).equals("1")){
            return true;
        }
        return false;
    }

    @RequestMapping(value = "/showPerson/{id}/{grade}" ,method = {RequestMethod.POST,RequestMethod.GET})
    public String setGoodStudent(@PathVariable String id,@PathVariable int grade){
        personService.setGoodStudent(id);
        return "redirect:/showPeople/"+grade;
    }

    public static org.jdom2.Document useSAXParser(String fileName) throws JDOMException,
            IOException {
        SAXBuilder saxBuilder = new SAXBuilder();
        return saxBuilder.build(new File(fileName));
    }
}
