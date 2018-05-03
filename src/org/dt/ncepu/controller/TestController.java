package org.dt.ncepu.controller;

import org.dt.ncepu.domain.Login;
import org.dt.ncepu.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/10/11 0011.
 */
@RestController
@RequestMapping(value = "/test" ,method = {RequestMethod.GET,RequestMethod.POST})
@SessionAttributes("testInfo")
public class TestController {

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/Login/{id}/{password}",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public boolean Login(@PathVariable String id, @PathVariable String password, Model model){
        Login login=new Login(id,password);
        if(personService.checkPassword(login).equals("1")){
            model.addAttribute("testInfo",login);
        }
        return true;
    }

    @RequestMapping(value = "/checkSession",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public boolean check(Model model){
        Login login=(Login)model.asMap().get("testInfo");
        if(login!=null&&personService.checkPassword(login).equals("1")){
            return true;
        }
        return false;
    }
}
