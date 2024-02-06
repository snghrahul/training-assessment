package com.training.aem.core.services;

import com.training.aem.core.models.ProductDetailsEntity;

public interface ProductService {

//    String getImage(String productId);
//    String getPrice(String productId);
    ProductDetailsEntity getFakeApiData(String apiUrl) throws Exception;
//    ProductDetailsEntity getProductById(String productId, ProductDetailsEntity productDetails);
}
