package com.training.aem.core.services;

import com.training.aem.core.models.ProductDetailsEntity;

public interface ProductInfoService {
    ProductDetailsEntity getProductInfo(int productId) throws Exception;
}
