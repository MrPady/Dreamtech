package org.dt.ncepu.domain;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/09/05 0005.
 */
public class Project implements Serializable{
    private int id;
    private long time;
    private String name;
    private String url;
    private String teamer;
    private String leader;
    private String timeString;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTeamer() {
        return teamer;
    }

    public void setTeamer(String teamer) {
        this.teamer = teamer;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public Project(int id, long time, String name, String url, String teamer, String leader) {

        this.id = id;
        this.time = time;
        this.name = name;
        this.url = url;
        this.teamer = teamer;
        this.leader = leader;
    }

    public Project() {

    }

    public String getTimeString() {
        return timeString;
    }

    public void setTimeString(String timeString) {
        this.timeString = timeString;
    }

    public boolean setTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = (Date) sdf.parse(timeString);
            time=date.getTime();
            return true;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
}
