package com.image.search.storage;

import com.image.search.domain.model.Image;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ImageStorageImpl implements ImageStorage{

    private volatile Map<String, Image> cache;

    public ImageStorageImpl() {
        this.cache = new HashMap<>();
    }

    @Override
    public Image get(String id) {
        return cache.get(id);
    }

    @Override
    public Collection<Image> getAll() {
        return cache.values();
    }

    @Override
    public void put(String id, Image image) {
        cache.put(id, image);
    }

    @Override
    public void update(Map<String, Image> map) {
        cache = map;
    }

    @Override
    public List<Image> find(String searchPhrase) {
        List<Image> resultList = new ArrayList<>();
        for (Image image : cache.values()) {
            String author = image.getAuthor();
            String camera = image.getCamera();

            if (image.getId().contains(searchPhrase)
                    || (author != null && author.contains(searchPhrase))
                    || (camera != null && camera.contains(searchPhrase))) {
                resultList.add(image);
            }
        }
        return resultList;
    }
}
