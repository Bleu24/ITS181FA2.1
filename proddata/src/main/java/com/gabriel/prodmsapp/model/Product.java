package com.gabriel.prodmsapp.model;

import lombok.Data;

@Data
public class Product {
    int id;
    int categoryId;
    String name;
    String description;

    @Override
    public String toString(){
        return name;
    }
}