package com.gabriel.prodmsapp;

import com.gabriel.prodmsapp.controller.storage.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class ProductMSApplication {
    public static void main(String[] args)
    {
        SpringApplication.run(ProductMSApplication.class, args);

    }
}