package org.dt.ncepu.domain;

import org.dt.ncepu.form.FieldForm;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/08/09 0009.
 */
public class Field implements Serializable {
    private int id;
    private String name;
    private boolean hasChild;
    private int fatherId;
    private String description;

    public Field() {
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Field(int id, String name, boolean hasChild, int fatherId, String description) {

        this.id = id;
        this.name = name;
        this.hasChild = hasChild;
        this.fatherId = fatherId;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHasChild() {
        return hasChild;
    }

    public void setHasChild(boolean hasChild) {
        this.hasChild = hasChild;
    }

    public int getFatherId() {
        return fatherId;
    }

    public void setFatherId(int fatherId) {
        this.fatherId = fatherId;
    }

    public Field(int id, String name, int fatherId) {
        this.id = id;
        this.name = name;
        this.hasChild = false;
        this.fatherId = fatherId;
    }

    public Field(int id, String name, boolean hasChild, int fatherId) {
        this.id = id;
        this.name = name;
        this.hasChild = hasChild;
        this.fatherId = fatherId;
    }

    public Field(FieldForm fieldForm){
        name=fieldForm.getName();
        fatherId= Integer.parseInt(fieldForm.getFatherId());
    }

}
