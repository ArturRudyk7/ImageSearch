package com.image.search.domain.service;

import com.image.search.domain.model.Image;
import com.image.search.storage.ImageStorage;
import com.image.search.storage.ImageStorageImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ImageServiceImpl implements ImageService{

    ExternalResourceServiceImpl externalResourceService;
    ImageStorage imageStorage;

    @Autowired
    public ImageServiceImpl(ExternalResourceServiceImpl externalResourceService, ImageStorageImpl imageStorage) {
        this.externalResourceService = externalResourceService;
        this.imageStorage = imageStorage;
    }

    public Image getImage(String id) {
        return imageStorage.get(id);
    }

    @Override
    public Collection<Image> getImageList() {
        return imageStorage.getAll();
    }

    @Override
    public Collection<Image> getImageList(String searchPhrase) {
        return imageStorage.find(searchPhrase);
    }
}
