package org.dt.ncepu.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/08/16 0016.
 */
public class Gallery {
    private int id;
    private String name;
    private String description;
    private int surfaceId;
    private long time;
    private List<Photo> photos=new ArrayList<>();

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public Gallery() {

    }

    public Gallery(int id, String name, String description, int surfaceId, long time) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.surfaceId = surfaceId;
        this.time = time;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSurfaceId() {
        return surfaceId;
    }

    public void setSurfaceId(int surfaceId) {
        this.surfaceId = surfaceId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
