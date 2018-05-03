package org.dt.ncepu.domain;

/**
 * Created by Administrator on 2017/08/16 0016.
 */
public class Photo {
    private String name;
    private int id;
    private String description;
    private long time;
    private int gallery;

    public int getGallery() {
        return gallery;
    }

    public void setGallery(int gallery) {
        this.gallery = gallery;
    }

    public Photo() {
    }

    public Photo(String name, int id, String description, long time, int gallery) {

        this.name = name;
        this.id = id;
        this.description = description;
        this.time = time;
        this.gallery = gallery;
    }

    public Photo(String name, int id, String description, long time) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.time = time;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
