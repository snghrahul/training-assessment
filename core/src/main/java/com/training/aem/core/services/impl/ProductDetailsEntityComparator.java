package com.training.aem.core.services.impl;

import com.training.aem.core.models.ProductDetailsEntity;

import java.util.Comparator;

public class ProductDetailsEntityComparator implements Comparator<ProductDetailsEntity> {

    boolean ascending = true;
    @Override
    public int compare(ProductDetailsEntity product1, ProductDetailsEntity product2) {
        if(ascending){
            return Double.compare(product1.getPrice(),product2.getPrice());
        }else{
            return Double.compare(product2.getPrice(),product1.getPrice());
        }
    }
}
