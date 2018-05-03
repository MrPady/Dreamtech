package org.dt.ncepu.domain;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/08/24 0024.
 */
public class ShortMessage implements Serializable{
    private String info;
    private String number;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public ShortMessage() {

    }

    public ShortMessage(String info, String number) {

        this.info = info;
        this.number = number;
    }
}
