package com.image.search.web.controller;

import com.image.search.domain.model.Image;
import com.image.search.domain.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/")
public class ImageController {

    @Autowired
    ImageService imageService;


    @GetMapping("images/{id}")
    public Image getImage(@PathVariable String id) {
        return imageService.getImage(id);
    }

    @GetMapping("images")
    public Collection<Image> getAllImages() {
        return imageService.getImageList();
    }

    @GetMapping("search/{searchTerm}")
    public Collection<Image> searchImages(@PathVariable("searchTerm") String searchPhrase) {
        return imageService.getImageList(searchPhrase);
    }
}
