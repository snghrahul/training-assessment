package com.training.aem.core.models;

import com.training.aem.core.models.ProductDetailsEntity;
import com.training.aem.core.services.SortedProductsService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Model(adaptables = {SlingHttpServletRequest.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SortedProductsModel {

    @OSGiService
    SortedProductsService sortedProductsService;

    @ValueMapValue
    String ascending;

    @ValueMapValue
    String descending;


    List<ProductDetailsEntity> productDetails = new ArrayList<>();
    List<ProductDetailsEntity> sortedProductDetails = new ArrayList<>();

//    public List<ProductDetailsEntity> getProductDetails(){
//
//        productDetails = sortedProductsService.getAllProducts("https://fakestoreapi.com/products/");
//        return productDetails;
//    }

    public List<ProductDetailsEntity> getSortedProductDetails(){
        sortedProductDetails = sortedProductsService.getSortedProducts();
        return sortedProductDetails;
    }


}
