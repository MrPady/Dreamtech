package org.dt.ncepu.domain;

import org.dt.ncepu.form.PersonForm;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/07/18 0018.
 */

public class Person implements Serializable {
    private String name;
    private int grade;
    private String description;
    private String id;
    private String mail;
    private String qq;
    private String phone;
    private String password;
    private boolean hasPic;
    private boolean show;

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public boolean isHasPic() {
        return hasPic;
    }

    public void setHasPic(boolean hasPic) {
        this.hasPic = hasPic;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public static Person PersonForm2Person(PersonForm personForm){
        Person person=new Person();
        if(personForm.getName()!=null&&!personForm.getName().trim().isEmpty()){
            person.setName(personForm.getName().trim());
        }
        if(personForm.getGrade()!=null&&!personForm.getName().trim().isEmpty()){
            int grade=0;
            try {
                grade=Integer.parseInt(personForm.getGrade().trim());
            }catch (NumberFormatException e){
                e.printStackTrace();
            }
            person.setGrade(grade);
        }
        if (personForm.getId()!=null&&!personForm.getId().trim().isEmpty()){
            person.setId(personForm.getId().trim());
        }
        if(personForm.getDescription()!=null&&!personForm.getDescription().trim().isEmpty()){
            person.setDescription(personForm.getDescription().trim());
        }
        if(personForm.getMail()!=null&&!personForm.getMail().trim().isEmpty()){
            person.setMail(personForm.getMail().trim());
        }
        if(personForm.getQq()!=null&&!personForm.getQq().trim().isEmpty()){
            person.setQq(personForm.getQq().trim());
        }
        if(personForm.getPhone()!=null&&!personForm.getPhone().trim().isEmpty()){
            person.setPhone(personForm.getPhone().trim());
        }
        return person;
    }
}
