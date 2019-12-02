package com.sisf.Pojo;

public class Cons_roots {
    private String title,name,description;
    private int imgId;

    public Cons_roots(String title, String name, String description, int imgId) {
        this.title = title;
        this.name = name;
        this.description = description;
        this.imgId = imgId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
