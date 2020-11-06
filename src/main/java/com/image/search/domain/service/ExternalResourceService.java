package com.image.search.domain.service;

import com.image.search.domain.model.Image;
import com.image.search.domain.model.ImageItemDto;

import java.util.List;

public interface ExternalResourceService {
    List<ImageItemDto> loadImageList(int page);
    Image loadExtendedImageData(String id);
}
