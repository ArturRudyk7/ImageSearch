package com.image.search.processor;

import com.image.search.domain.model.Image;
import com.image.search.domain.model.ImageItemDto;
import com.image.search.domain.service.ExternalResourceServiceImpl;
import com.image.search.storage.ImageStorage;
import com.image.search.storage.ImageStorageImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ImageProcessor {

    private ExternalResourceServiceImpl externalResourceService;

    private ImageStorage imageStorage;

    @Autowired
    public ImageProcessor(ExternalResourceServiceImpl externalResourceService, ImageStorageImpl imageStorage) {
        this.externalResourceService = externalResourceService;
        this.imageStorage = imageStorage;
    }

    @PostConstruct
    public void initializeCache() {
        for (Image image : loadAllImagesFromExternalSource()) {
            imageStorage.put(image.getId(), image);
        }
    }

    @Scheduled(initialDelayString = "${cache.reload.period}", fixedDelayString = "${cache.reload.period}")
    public void reloadCache() {
        Map<String, Image> map = new HashMap<>();
        for (Image image : loadAllImagesFromExternalSource()) {
            map.put(image.getId(), image);
        }
        imageStorage.update(map);
    }

    public List<Image> loadAllImagesFromExternalSource() {
        List<ImageItemDto> list = new ArrayList<>();
        List<ImageItemDto> tempList = externalResourceService.loadImageList(1);
        for (int i = 1; tempList.size() > 0; i++) {
            list.addAll(tempList);
            tempList = externalResourceService.loadImageList(i);
        }
        return list.stream()
                .map(ImageItemDto::getId)
                .map(externalResourceService::loadExtendedImageData)
                .collect(Collectors.toList());
    }

}
