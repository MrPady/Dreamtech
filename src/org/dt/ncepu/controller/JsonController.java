package org.dt.ncepu.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dt.ncepu.domain.*;
import org.dt.ncepu.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**L
 * Created by Administrator on 2017/07/20 0020.
 */
@RestController
@SessionAttributes("userInfo")
@RequestMapping(value = "/json" ,method = {RequestMethod.GET,RequestMethod.POST})
public class JsonController {

    private static final Log loggers=
            LogFactory.getLog(JsonController.class);

    @Autowired
    private PersonService personService;

    @Autowired
    private FieldService fieldService;

    @Autowired
    private ArticleService articleService;


    @Autowired
    private PhotoService photoService;

    @Autowired
    private BillService billService;

    @Autowired
    private ProjectService projectService;

//    @RequestMapping(value = "/personDetail/{personId}",method = {RequestMethod.GET,RequestMethod.POST})
//    @ResponseBody
//    public Person getPersonInJson(@PathVariable String personId){
//        loggers.info(personId);
//        return personService.getPersonDetailsFromId(personId,false);
//    }
//
//    @RequestMapping(value = "/personDetail/{personId}/{usrId}/{password}",method = {RequestMethod.GET,RequestMethod.POST})
//    @ResponseBody
//    public Person getPersonInJson(@PathVariable String personId,@PathVariable String usrId,@PathVariable String password){
//        loggers.info(personId);
//        return personService.getPersonDetailsFromId(personId,true);
//    }



    @RequestMapping(value = "/fieldList",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public List<Field> getFiledsInJson(){
        loggers.info("fieldList:");
        return fieldService.getFields();
    }

    @RequestMapping(value = "/articleList/{field}",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public List<Article> getArticlesInJson(@PathVariable int field){
        loggers.info("articleList:"+field);
        return articleService.getArticles(field);
    }

    @RequestMapping(value = "/article/{id}",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Article getFullArticleInJson(@PathVariable int id){
        loggers.info("article:"+id);
        return articleService.getFullArticle(id);
    }


    @RequestMapping(value = "/maxGrade",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Grade getMaxGrade(){
        return new Grade();
    }

    @RequestMapping(value = "/gallery/{id}",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Gallery getGalleryDetails(@PathVariable int id){
        return photoService.getGallery(id);
    }

    @RequestMapping(value = "/galleries",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public List<Gallery> getGalleries(){
        List<Gallery> galleries=photoService.getGalleries();
        for (Gallery gallery: galleries) {
            gallery.setPhotos( photoService.getPhotos(gallery.getId()));
        }
        return galleries;
    }

    @RequestMapping(value = "/photos/{gallery}",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public List<Photo> getPhotoss(@PathVariable int gallery){
        return photoService.getPhotos(gallery);
    }

    @RequestMapping(value = "/bills",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public List<Bill> getBills(){
        return billService.showBills();
    }

    @RequestMapping(value = "/projects",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public List<Project> getProjectss(){
        return projectService.showProjects();
    }

    @RequestMapping(value = "/recruit",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public boolean isRecruiting(){
        return personService.isRecruiting();
    }

    @RequestMapping(value = "/goodStudents",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public List<Person> getGoodStudents(){
        return personService.getGoodStudents();
    }
}
