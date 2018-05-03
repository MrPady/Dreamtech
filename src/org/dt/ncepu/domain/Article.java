package org.dt.ncepu.domain;

import org.dt.ncepu.form.ArticleForm;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/08/09 0009.
 */
public class Article implements Serializable {
    private String title;
    private String body;
    private long time;
    private String writer;
    private int id;
    private int field;

    public String getTimeString() {
        return timeString;
    }

    public void setTimeString(String timeString) {
        this.timeString = timeString;
    }

    private String timeString;

    public int getField() {
        return field;
    }

    public void setField(int field) {
        this.field = field;
    }

    public Article(String title, String body, long time, String writer, int id, int field) {
        this.title = title;
        this.body = body;
        this.time = time;
        this.writer = writer;
        this.id = id;
        this.field=field;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        setTimeString(sdf.format(new Date(time)));
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        setTimeString(sdf.format(new Date(time)));
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Article(String title, long time, String writer, int id, int field) {
        this.title = title;
        this.time = time;
        this.writer = writer;
        this.id = id;
        this.field = field;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        setTimeString(sdf.format(new Date(time)));
    }

    public Article() {
    }

    public Article (ArticleForm articleForm){
        title=articleForm.getTitle();
        writer=articleForm.getWriter();
        field= Integer.parseInt(articleForm.getField());
        time=articleForm.getTime();
    }

    public static List<Article> setAllStringTime(List<Article> articles){
        for(Article article:articles){
            long time=article.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            article.setTimeString(sdf.format(new Date(time)));
        }
        return articles;
    }
}
