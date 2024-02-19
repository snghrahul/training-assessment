package com.training.aem.core.services.impl;

import com.training.aem.core.bean.ProductDetailsEntity;

import java.util.Comparator;

public class ProductDetailsEntityComparator implements Comparator<ProductDetailsEntity> {

    private String sortType;
    public ProductDetailsEntityComparator(String sortType) {
        this.sortType = sortType;
    }
    @Override
    public int compare(ProductDetailsEntity product1, ProductDetailsEntity product2) {
        if("ascending".equalsIgnoreCase(sortType)){
            return Double.compare(product1.getPrice(),product2.getPrice());
        } else if ("mostRecent".equalsIgnoreCase(sortType)) {
            return Integer.compare(product2.getId(),product1.getId());

        } else{
            return Double.compare(product2.getPrice(),product1.getPrice());
        }
    }
}
