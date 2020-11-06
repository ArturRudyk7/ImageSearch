package com.image.search.storage;

import com.image.search.domain.model.Image;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface ImageStorage {
    Image get(String id);
    Collection<Image> getAll();
    void put(String id, Image image);
    void update(Map<String, Image> map);
    List<Image> find(String searchPhrase);
}
