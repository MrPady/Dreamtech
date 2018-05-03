package org.dt.ncepu.form;

/**
 * Created by Administrator on 2017/08/12 0012.
 */
public class ArticleForm {
    private long time;
    private String body;
    private String writer;
    private String title;
    private String field;

    public long getTime() {
        return time;
    }

    public ArticleForm(String body, String writer, String title, String field) {
        this.body = body;
        this.writer = writer;
        this.title = title;
        this.field = field;
    }

    public ArticleForm(long time, String body, String writer, String title, String field) {

        this.time = time;
        this.body = body;
        this.writer = writer;
        this.title = title;
        this.field = field;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
