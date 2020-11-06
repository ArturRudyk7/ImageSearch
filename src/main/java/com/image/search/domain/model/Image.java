package com.image.search.domain.model;

public class Image {

    public String id;
    public String author;
    public String camera;
    public String cropped_picture;
    public String full_picture;

    public Image() {
    }

    public Image(String id, String author, String camera, String cropped_picture, String full_picture) {
        this.id = id;
        this.author = author;
        this.camera = camera;
        this.cropped_picture = cropped_picture;
        this.full_picture = full_picture;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getCropped_picture() {
        return cropped_picture;
    }

    public void setCropped_picture(String cropped_picture) {
        this.cropped_picture = cropped_picture;
    }

    public String getFull_picture() {
        return full_picture;
    }

    public void setFull_picture(String full_picture) {
        this.full_picture = full_picture;
    }

}
