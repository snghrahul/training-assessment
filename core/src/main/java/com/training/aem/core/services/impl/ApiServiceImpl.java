package com.training.aem.core.services.impl;

import com.training.aem.core.bean.ProductDetailsEntity;
import com.training.aem.core.services.ApiService;
import com.training.aem.core.services.PageCreationService;
import com.training.aem.core.services.ProductService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = ApiService.class)
public class ApiServiceImpl implements ApiService {

    @Reference
    PageCreationService pageCreationService;

    @Reference
    ProductService productService;


    ProductDetailsEntity productDetails = new ProductDetailsEntity();
    @Override
    public void fetchDataAndCreatePages() throws Exception {

        productDetails = productService.getFakeApiData("https://fakestoreapi.com/products/1");
        pageCreationService.createPages(productDetails);

    }
}
