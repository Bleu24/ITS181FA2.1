package com.gabriel.prodmsv.ServiceImpl;

import com.gabriel.prodmsapp.model.Product;
import com.gabriel.prodmsapp.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Value("${service.api.endpoint}")
    private String endpointUrl = "http://localhost:8080/api/product";

    RestTemplate restTemplate = null;

    public RestTemplate getRestTemplate() {
        if (restTemplate == null) {
            restTemplate = new RestTemplate();
            List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
            MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
            converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
            messageConverters.add(converter);
            restTemplate.setMessageConverters(messageConverters);
        }
        return restTemplate;
    }

    @Override
    public Product getProduct(Integer id) {
        String url = endpointUrl + "/" + Integer.toString(id);
        logger.info("getProduct: " + url);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity request = new HttpEntity<>(null, headers);
        final ResponseEntity<Product> response =
                getRestTemplate().exchange(url, HttpMethod.GET, request, Product.class);
        return response.getBody();
    }

    @Override
    public Product[] getProducts() {
        String url = endpointUrl;
        logger.info("getProducts: " + url);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity request = new HttpEntity<>(null, headers);
        final ResponseEntity<Product[]> response =
                getRestTemplate().exchange(url, HttpMethod.GET, request, Product[].class);
        Product[] products = response.getBody();
        return products;

    }


    @Override
    public Product create(Product product) {
        String url = endpointUrl;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Product> request = new HttpEntity<>(product, headers);
        final ResponseEntity<Product> response =
                getRestTemplate().exchange(url, HttpMethod.PUT, request, Product.class);
        return response.getBody();
    }

    @Override
    public Product update(Product product) {
        logger.info("update: " + product.toString());
        String url = endpointUrl;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Product> request = new HttpEntity<>(product, headers);
        final ResponseEntity<Product> response =
                getRestTemplate().exchange(url, HttpMethod.POST, request, Product.class);
        return response.getBody();
    }

    @Override
    public void delete(Integer id) {
        logger.info("delete: " + Integer.toString(id));
        String url = endpointUrl + "/" + Integer.toString(id);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Product> request = new HttpEntity<>(null, headers);
        final ResponseEntity<Product> response =
                getRestTemplate().exchange(url, HttpMethod.DELETE, request, Product.class);
    }
}