package com.image.search.domain.service;

import com.image.search.domain.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class ExternalResourceServiceImpl implements ExternalResourceService{

    @Value("${external.url.images}")
    String imagesExternalUrl;

    @Value("${external.url.auth}")
    String authExternalUrl;

    @Value("${apikey}")
    String apiKey;

    RestTemplate restTemplate;

    String authToken = "";

    public ExternalResourceServiceImpl() {
        this.restTemplate = new RestTemplateBuilder().build();
    }

    private void authentificate() {
        HttpEntity request = createAuthentificateRequest();
            ResponseEntity<AuthentificationResponse> responseEntity = restTemplate.postForEntity(authExternalUrl, request, AuthentificationResponse.class);
            authToken = responseEntity.getBody().getToken();
    }

    @Override
    public List<ImageItemDto> loadImageList(int page) {
        if (authToken.isBlank()) {
            authentificate();
        }
        HttpEntity httpEntity = createImageRequest();
        String url = String.format("%s?page=%s&limit=20", imagesExternalUrl, page);
        ResponseEntity<ImagesResponse> image = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ImagesResponse.class);
        return image.getBody().getPictures();
    }

    @Override
    public Image loadExtendedImageData(String id) {
        if (authToken.isBlank()) {
            authentificate();
        }
        HttpEntity httpEntity = createImageRequest();
        String url = imagesExternalUrl + "/" + id;
        ResponseEntity<Image> image = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Image.class);
        return image.getBody();
    }

    private HttpEntity createAuthentificateRequest() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity(apiKey, headers);
    }

    private HttpEntity createImageRequest() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authToken);
        return new HttpEntity(headers);
    }
}
