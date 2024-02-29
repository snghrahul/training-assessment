package com.training.aem.core.models.impl;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.training.aem.core.bean.ProductDetailsEntity;
import com.training.aem.core.models.ProductDetailsByIdModel;
import com.training.aem.core.services.ProductService;
import org.apache.sling.api.SlingException;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import javax.annotation.PostConstruct;
import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = {ProductDetailsByIdModel.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ProductsDetailsByIdModelImpl implements ProductDetailsByIdModel {

    @OSGiService
    ProductService productService;

    @SlingObject
    SlingHttpServletRequest request;

    @OSGiService
    PageManager pageManager;

    private ProductDetailsEntity productDetails;
    private Page currentPage;
    private String productId;
    private static  final Logger logger = LoggerFactory.getLogger(ProductsDetailsByIdModelImpl.class);

    @PostConstruct
    protected void init() throws Exception {
        try{
            Resource currentResource = request.getResource();
            pageManager = currentResource.getResourceResolver().adaptTo(PageManager.class);
            currentPage = pageManager.getContainingPage(currentResource);
            if(currentPage != null){
                ValueMap properties = currentPage.getProperties();
                if(properties.containsKey("productId")){
                    productId = properties.get("productId",String.class);
                }
            }
        }catch (SlingException e){
            logger.error("Current Page is Null: " + currentPage);
        }

        productDetails = productService.getFakeApiData("https://fakestoreapi.com/products/" + productId);
    }
    @Override
    public ProductDetailsEntity getProductDetails() {
        return productDetails;
    }


}
