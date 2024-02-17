package com.training.aem.core.services;

import com.training.aem.core.bean.ProductDetailsEntity;

public interface ProductInfoService {
    ProductDetailsEntity getProductInfo(String productId) throws Exception;
}
