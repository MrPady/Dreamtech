package org.dt.ncepu.domain;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/08/23 0023.
 */
public class Bill implements Serializable{
    private int id;
    private long time;
    private float money;
    private String detail;
    private String timeString;


    private static final Log loggers =
            LogFactory.getLog(Bill.class);
    public String getTimeString() {
        return timeString;
    }

    public void setTimeString(String timeString) {
        this.timeString = timeString;
    }

    public int getId() {
        return id;
    }

    public Bill(long time, float money, String detail) {
        this.time = time;
        this.money = money;
        this.detail = detail;
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

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Bill(int id, long time, float money, String detail) {
        this.id = id;
        this.time = time;
        this.money = money;
        this.detail = detail;
    }

    public Bill() {
    }

    public static List<Bill> setAllStringTime(List<Bill> bills){
        for(Bill bill:bills){
            long time=bill.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            bill.setTimeString(sdf.format(new Date(time)));
        }
        return bills;

    }

    public boolean setTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            loggers.info(timeString);
            Date date = (Date) sdf.parse(timeString);
            time=date.getTime();
            return true;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
}
