package com.image.search.domain.model;

import java.util.List;

public class ImagesResponse {
    private List<ImageItemDto> pictures;

    public ImagesResponse() {

    }

    public ImagesResponse(List<ImageItemDto> pictures) {
        this.pictures = pictures;
    }

    public List<ImageItemDto> getPictures() {
        return pictures;
    }

    public void setPictures(List<ImageItemDto> pictures) {
        this.pictures = pictures;
    }

}
