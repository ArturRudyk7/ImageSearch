package com.image.search.domain.service;

import com.image.search.domain.model.Image;

import java.util.Collection;

public interface ImageService {

    public Image getImage(String id);

    public Collection<Image> getImageList();

    public Collection<Image> getImageList(String searchPhrase);

}
