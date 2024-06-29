package com.gabriel.prodmsv.ServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileServiceImpl {
    Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Value("${service.api.endpoint}")
    private String endpointUrl = "http://localhost:8080/api";

    RestTemplate restTemplate = null;

    public RestTemplate getRestTemplate() {
        if(restTemplate == null) {
            restTemplate = new RestTemplate();
            List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();

            FormHttpMessageConverter converter = new FormHttpMessageConverter();
            messageConverters.add(converter);
            converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
            messageConverters.add(converter);
            converter.setSupportedMediaTypes(Arrays.asList( MediaType.MULTIPART_FORM_DATA));
            messageConverters.add(converter);

            restTemplate.setMessageConverters(messageConverters);
        }
        return restTemplate;
    }

    public void uploadStream(String filename){
        String url = endpointUrl + "/upload";

        try{
            File file = new File(filename);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.valueOf("multipart/form-data&nbsp;boundary=--------------------------434847364711032977518267"));

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("file",  file);

            HttpEntity<MultiValueMap<String,Object>> requestEntity = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = getRestTemplate().postForEntity(url, requestEntity, String.class);
        }
        catch(Exception ex){
            System.out.println("Error :" + ex.getMessage());
        }
    }


    public void upload(String filename) {
        logger.info("upload: " + filename);
        String url = endpointUrl + "/upload";
        File file = new File(filename);

        HttpHeaders parts = new HttpHeaders();
        parts.setContentType(MediaType.IMAGE_JPEG);;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf("multipart/form-data; boundary=--------------------------434847364711032977518267"));

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", filename);

        HttpEntity<MultiValueMap<String,Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = getRestTemplate().postForEntity(url, requestEntity, String.class);
    }

    public void download(String filename) {
        logger.info("download: " + filename);
        String url = endpointUrl + "/download/" + filename;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("file", filename);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        final ResponseEntity<String> response =
                getRestTemplate().postForEntity(url, request, String.class);
    }
}