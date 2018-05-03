package org.dt.ncepu.domain;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/09/28 0028.
 */
public class Login implements Serializable{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Login() {
        name="123";
        password="123";
    }

    public Login(String name, String password) {

        this.name = name;
        this.password = password;
    }

    private String password;
}
