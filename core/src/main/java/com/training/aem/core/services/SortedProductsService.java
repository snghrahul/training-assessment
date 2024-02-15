package com.training.aem.core.services;

import com.training.aem.core.models.ProductDetailsEntity;

import java.util.List;

public interface SortedProductsService {
    List<ProductDetailsEntity> getSortedProducts(String sortType);
    List<ProductDetailsEntity> getAllProducts(String url);
}
