package org.dt.ncepu.form;

/**
 * Created by Administrator on 2017/08/14 0014.
 */
public class FieldForm {
    private String name;
    private String fatherId;
    private boolean hasChild;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FieldForm(String name, String fatherId, boolean hasChild, String description) {

        this.name = name;
        this.fatherId = fatherId;
        this.hasChild = hasChild;
        this.description = description;
    }

    public FieldForm(String name, String fatherId, boolean hasChild) {
        this.name = name;
        this.fatherId = fatherId;
        this.hasChild = hasChild;
    }

    public FieldForm(){

    }

    public FieldForm(String name, String fatherId) {
        this.name = name;
        this.fatherId = fatherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFatherId() {
        return fatherId;
    }

    public void setFatherId(String fatherId) {
        this.fatherId = fatherId;
    }

    public boolean isHasChild() {
        return hasChild;
    }

    public void setHasChild(boolean hasChild) {
        this.hasChild = hasChild;
    }
}
