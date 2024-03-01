package com.training.aem.core.services.impl;

import com.training.aem.core.bean.ProductDetailsEntity;
import com.training.aem.core.services.ProductInfoService;
import com.training.aem.core.services.ProductService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = ProductInfoService.class)
public class ProductInfoServiceImpl implements ProductInfoService{
    @Reference
    ProductService productService;
    @Override
    public ProductDetailsEntity getProductInfo(String productId) throws Exception {
        ProductDetailsEntity productDetails = productService.getFakeApiData("https://fakestoreapi.com/products/" + productId);
        return productDetails;
    }
}
