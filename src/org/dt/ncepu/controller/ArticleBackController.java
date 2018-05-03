package org.dt.ncepu.controller;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dt.ncepu.domain.Article;
import org.dt.ncepu.domain.Field;
import org.dt.ncepu.service.ArticleService;
import org.dt.ncepu.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/08/14 0014.
 */
@Controller
public class ArticleBackController {

    private static final Log loggers =
            LogFactory.getLog(ArticleBackController.class);

    public static String path=System.getProperty("pady.webapp")+"\\src\\pic\\fields";

    @Autowired
    private ArticleService articleService;


    @Autowired
    private FieldService fieldService;


    @RequestMapping(value = "/saveArticle", method = {RequestMethod.GET, RequestMethod.POST})
    public String saveArticle(Article article) {
        article.setTime(System.currentTimeMillis());
        articleService.addArticle(article);
        loggers.info(article.getBody()+article.getField()+article.getTitle()+" "+article.getTimeString());
        return "redirect:/showArticles/" + article.getField();
    }

    @RequestMapping(value = "/showArticles/{field}", method = {RequestMethod.GET, RequestMethod.POST})
    public String showArticles(Model model, @PathVariable int field) {
        List<Field> fields= fieldService.getFields();
        List<Article> articles=articleService.getArticles(field);
        model.addAttribute("fields",fields);
        model.addAttribute("articles",articles);
        return "ArticleList";
    }

    @RequestMapping(value = "/addArticle", method = {RequestMethod.GET, RequestMethod.POST})
    public String addArticle(Model model) {
        model.addAttribute("article",new Article());
        model.addAttribute("fields",fieldService.getFields());
        return "ArticleForm";
    }

    @RequestMapping(value = "/deleteArticle/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public String deleteArticles(Model model,@PathVariable String id) {
        int field=articleService.getFullArticle(Integer.parseInt(id)).getField();
        articleService.deleteArticle(id);
        model.addAttribute("Fields",fieldService.getFields());
        return "redirect:/showArticles/"+field;
    }

    @RequestMapping(value = "/showFields", method = {RequestMethod.GET, RequestMethod.POST})
    public String showFields(Model model) {
        model.addAttribute("Fields",fieldService.getFields());
        return "FieldList";
    }

    @RequestMapping(value = "/deleteField/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public String deleteField(@PathVariable int id) {
        String result=fieldService.deleteField(id);
        loggers.info(result);
        return "redirect:/showFields";
    }

    @RequestMapping(value = "/saveField", method = {RequestMethod.GET, RequestMethod.POST})
    public String saveField(@RequestParam("file") MultipartFile file,Field field) {
        fieldService.addField(field);
        field=fieldService.getFieldFromName(field.getName());
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path,field.getId()+".jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/showFields";
    }

    @RequestMapping(value = "/addField", method = {RequestMethod.GET, RequestMethod.POST})
    public String addField(Model model) {
        model.addAttribute("field",new Field());
        model.addAttribute("fields",fieldService.getFields());
        return "FieldForm";
    }

    @RequestMapping(value = "/editField/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public String editField(Model model,@PathVariable int id) {
        Field field=fieldService.getFieldFromId(id);
        model.addAttribute("field",field);
        if(field.getFatherId()!=0){
            model.addAttribute("fatherName",fieldService.getFieldFromId(field.getFatherId()).getName());
        }else {
            model.addAttribute("fatherName","无");
        }
        model.addAttribute("Fields",fieldService.getFields());
        return "FieldDetails";
    }

    @RequestMapping(value = "/saveEditedField", method = {RequestMethod.GET, RequestMethod.POST})
    public String saveEditedField(Field field) {
        fieldService.editField(field);
        return "FieldList";
    }

}
