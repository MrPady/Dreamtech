package org.dt.ncepu.controller;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dt.ncepu.domain.Gallery;
import org.dt.ncepu.domain.Photo;
import org.dt.ncepu.helper.ImgHelper;
import org.dt.ncepu.service.PhotoService;
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

/**
 * Created by Administrator on 2017/08/18 0018.
 */
@Controller
public class GalleryBackController {
    private static final Log loggers =
            LogFactory.getLog(GalleryBackController.class);

    public static String path=System.getProperty("pady.webapp")+"src\\pic\\galleries\\";

    @Autowired
    private PhotoService photoService;

    @RequestMapping(value = "/addGallery", method = {RequestMethod.GET, RequestMethod.POST})
    public String addGallery(Model model) {
        model.addAttribute("gallery", new Gallery());
        return "GalleryForm";
    }

    @RequestMapping(value = "/showGalleries", method = {RequestMethod.GET, RequestMethod.POST})
    public String showGalleries(Model model) {
        model.addAttribute("galleries", photoService.getGalleries());
        model.addAttribute("path",path);
        return "GalleryList";
    }

    @RequestMapping(value = "/deleteGallery/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public String deleteGallery(@PathVariable int id) {
        photoService.deleteGallery(id);
        return "redirect:/showGalleries";
    }

    @RequestMapping(value = "/saveGallery", method = {RequestMethod.GET, RequestMethod.POST})
    public String saveGallery(Gallery gallery) {
        gallery.setSurfaceId(-1);
        gallery.setTime(System.currentTimeMillis());
        photoService.addGallery(gallery);
        loggers.info(gallery.getName());
        return "redirect:/showGalleries";
    }

//    @RequestMapping(value = "/editGallery/{gallery}", method = {RequestMethod.GET, RequestMethod.POST})
//    public String editGallery(Model model, @PathVariable int gallery) {
//        model.addAttribute("gallery", gallery);
//        return "redirect:/showPhotos/"+gallery;
//    }

    @RequestMapping(value = "/saveEditedGallery", method = {RequestMethod.GET, RequestMethod.POST})
    public String saveEditedGallery(Gallery gallery) {
        photoService.editGallery(gallery);
        return "redirect:/showGalleries";
    }

    @RequestMapping(value = "/showPhotos/{gallery}", method = {RequestMethod.GET, RequestMethod.POST})
    public String showPhotos(Model model, @PathVariable int gallery) {
        model.addAttribute("photos", photoService.getPhotos(gallery));
        model.addAttribute("gallery",gallery);
        return "PhotoList";
    }

    @RequestMapping(value = "/addPhoto/{gallery}", method = {RequestMethod.GET, RequestMethod.POST})
    public String addPhoto(Model model, @PathVariable int gallery) {
        model.addAttribute("photo", new Photo());
        model.addAttribute("gallery",gallery);
        return "PhotoForm";
    }

    @RequestMapping(value = "/deletePhoto/{id}/{gallery}", method = {RequestMethod.GET, RequestMethod.POST})
    public String deletePhoto(@PathVariable int id,@PathVariable int gallery) {
        photoService.deletePhoto(id);
        return "redirect:/showPhotos/"+gallery;
    }

    @RequestMapping(value = "/savePhoto/{gallery}", method = {RequestMethod.GET, RequestMethod.POST})
    public String savePhoto(@RequestParam("file") MultipartFile file, Photo photo, @PathVariable int gallery) {
        long time=System.currentTimeMillis();
        photo.setTime(time);
        photo.setGallery(gallery);
        photoService.addPhoto(photo);
        int id=photoService.getPhotoId(time);
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path, String.valueOf(id)+".jpg"));
//            ImgHelper.Tosmallerpic(
//                    System.getProperty("pady.webapp")+"src\\pic\\smallGalleries\\",
//                    new File(path,String.valueOf(id)+".jpg"),
//                    "_middle",String.valueOf(id)+".jpg",300,200);
            ImgHelper.PadyCutAndZoomImage(
                    path+String.valueOf(id)+".jpg",
                    new File(System.getProperty("pady.webapp")+"src\\pic\\smallGalleries\\",String.valueOf(id)+"_middle.jpg"),
                    240,160
                    );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/showPhotos/"+gallery;
    }

    @RequestMapping(value = "/editPhoto/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public String editPhoto(@PathVariable int id,Model model) {
        Photo photo=photoService.getPhoto(id);
        model.addAttribute("photo",photo);
        return "PhotoDetails";
    }

    @RequestMapping(value = "/saveEditedPhoto/{id}/{gallery}", method = {RequestMethod.GET, RequestMethod.POST})
    public String saveEditedPhoto(Photo photo,@PathVariable int id,@PathVariable int gallery) {
        photoService.editPhoto(photo,id);
        return "redirect:/showPhotos/"+gallery;
    }
}
