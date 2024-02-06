package com.training.aem.core.models;


import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.training.aem.core.services.ProductService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import javax.annotation.PostConstruct;
import org.apache.sling.api.resource.Resource;

@Model(adaptables = SlingHttpServletRequest.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ProductDetailsModel {

    @OSGiService
    ProductService productService;

    @SlingObject
    SlingHttpServletRequest request;

   @OSGiService
    ResourceResolver resourceResolver;


    @OSGiService
    PageManager pageManager;



    private ProductDetailsEntity productDetails;
    private Page currentPage;
    private String productId;

    @PostConstruct
    protected void init() throws Exception {
        Resource currentResource = request.getResource();
        pageManager = currentResource.getResourceResolver().adaptTo(PageManager.class);
        currentPage = pageManager.getContainingPage(currentResource);
        if(currentPage != null){
            ValueMap properties = currentPage.getProperties();
            if(properties.containsKey("productId")){
                 productId = properties.get("productId",String.class);
            }
        }
        productDetails = productService.getFakeApiData("https://fakestoreapi.com/products/" + productId);
    }
    public ProductDetailsEntity getProductDetails() {
        return productDetails;
    }
}
