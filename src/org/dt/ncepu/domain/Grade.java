package org.dt.ncepu.domain;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

import static org.dt.ncepu.controller.PersonBackController.useSAXParser;

/**
 * Created by Administrator on 2017/07/29 0029.
 */
public class Grade implements Serializable {

    public static String path=System.getProperty("pady.webapp");

    public static void AddMaxGrade (){
        Document document= null;
        try {
            document = useSAXParser(path+"\\WEB-INF\\info.xml");
            Element root = document.getRootElement();
            int i= Integer.parseInt(root.getChildText("maxGrade"));
            i++;
            root.getChild("maxGrade").setText(String.valueOf(i));
            XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
            xmlOutputter.output(document, new FileOutputStream(path+"\\WEB-INF\\info.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JDOMException e) {
            e.printStackTrace();
        }
    }
    public static int GetMaxGrade(){
        Document document= null;
        int i=2016;
        try {
            document = useSAXParser(path+"\\WEB-INF\\info.xml");
            Element root = document.getRootElement();
            i= Integer.parseInt(root.getChildText("maxGrade"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JDOMException e) {
            e.printStackTrace();
        }
        return i;
    }

    private int year;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Grade() {
        this.year = GetMaxGrade();
    }
}
