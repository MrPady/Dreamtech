package org.dt.ncepu.controller;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dt.ncepu.domain.ShortMessage;
import org.dt.ncepu.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

/**
 * Created by Administrator on 2017/08/24 0024.
 */
@Controller
public class SMSBackController {
    private static final Log loggers =
            LogFactory.getLog(SMSBackController.class);

    @Autowired
    private PersonService personService;

   @RequestMapping(value = "/editMsg",method = {RequestMethod.POST,RequestMethod.GET})
    public String editMsg(Model model){
       model.addAttribute("shortMsg",new ShortMessage());
       return "SMSForm";
   }
    @RequestMapping(value = "/sendMsg",method = {RequestMethod.POST,RequestMethod.GET})
    public String sendMsg(Model model, ShortMessage shortMessage){
        org.apache.commons.httpclient.HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("http://utf8.api.smschinese.cn");
        post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");//在头文件中设置转码
        NameValuePair[] data ={ new NameValuePair("Uid", "pady"),new NameValuePair("Key", "664155b4c5933adba717"),
                new NameValuePair("smsMob",personService.getNumbers(shortMessage.getNumber())),
                new NameValuePair("smsText",shortMessage.getInfo())};
        post.setRequestBody(data);
        try {
            client.executeMethod(post);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Header[] headers = post.getResponseHeaders();
        int statusCode = post.getStatusCode();
        loggers.info("statusCode:"+statusCode);
//        for(Header h : headers)
//        {
//            System.out.println(h.toString());
//        }
        String result = null;
        try {
            result = new String(post.getResponseBodyAsString().getBytes("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        loggers.info(result);
        model.addAttribute("successNumber",result);
        post.releaseConnection();
        return "SMSSuccessForm";
    }


}
