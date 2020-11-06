package com.image.search.domain.model;

public class ImageItemDto {

    private String id;
    private String cropped_picture;

    public ImageItemDto() {
    }

    public ImageItemDto(String id, String cropped_picture) {
        this.id = id;
        this.cropped_picture = cropped_picture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCropped_picture() {
        return cropped_picture;
    }

    public void setCropped_picture(String cropped_picture) {
        this.cropped_picture = cropped_picture;
    }
}
