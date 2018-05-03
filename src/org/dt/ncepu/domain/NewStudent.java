package org.dt.ncepu.domain;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/09/24 0024.
 */
public class NewStudent implements Serializable{
    private String id;
    private String name;
    private String qq;
    private String phone;
    private String nClass;
    private long time;
    private String description;
    private boolean read;
    private String direction;
    private boolean isAGirl;
    private String readString;

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public boolean isAGirl() {
        return isAGirl;
    }

    public String getReadString() {
        return readString;
    }

    public void setReadString(String readString) {
        this.readString = readString;
    }

    public void setAGirl(boolean AGirl) {
        isAGirl = AGirl;
    }

    public NewStudent(String id, String name, String qq, String phone, String nClass, long time, String description, boolean read, String direction, boolean isAGirl) {

        this.id = id;
        this.name = name;
        this.qq = qq;
        this.phone = phone;
        this.nClass = nClass;
        this.time = time;
        this.description = description;
        this.read = read;
        this.direction = direction;
        this.isAGirl = isAGirl;
        if(read){
            readString="已阅读";
        }else {
            readString="未阅读";
        }
    }

    public NewStudent() {
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getnClass() {
        return nClass;
    }

    public void setnClass(String nClass) {
        this.nClass = nClass;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
        if(read){
            readString="已阅读";
        }else {
            readString="未阅读";
        }
    }

    public NewStudent(String id, String name, String qq, String phone, String nClass, long time, String description, boolean read) {

        this.id = id;
        this.name = name;
        this.qq = qq;
        this.phone = phone;
        this.nClass = nClass;
        this.time = time;
        this.description = description;
        this.read = read;
        if(read){
            readString="已阅读";
        }else {
            readString="未阅读";
        }
    }
}
