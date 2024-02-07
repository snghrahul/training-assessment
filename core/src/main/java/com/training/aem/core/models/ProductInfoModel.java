package com.training.aem.core.models;


import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.training.aem.core.services.ProductInfoService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.request.RequestPathInfo;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;

import javax.annotation.PostConstruct;

@Model(adaptables = {SlingHttpServletRequest.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ProductInfoModel {

    @OSGiService
    private ProductInfoService productInfoService;

     @OSGiService
     PageManager pageManager;
    @ScriptVariable
    SlingHttpServletRequest request;

    private ProductDetailsEntity productDetails;
    private Page currentPage;
    private String productId;

    @PostConstruct
    protected void init() throws Exception {

//        StringBuilder fullUrl = new StringBuilder(request.getRequestURL().toString());
        RequestPathInfo pathInfo = request.getRequestPathInfo();
        String urlSuffix = pathInfo.getSuffix();
        String productId = urlSuffix.substring(1);

        productDetails = productInfoService.getProductInfo(productId);
    }
    public ProductDetailsEntity getProductDetails() {
        return productDetails;
    }

//    public String toGetProductId(){
//        Resource currentResource = request.getResource();
//        pageManager = currentResource.getResourceResolver().adaptTo(PageManager.class);
//        currentPage = pageManager.getContainingPage(currentResource);
//        if(currentPage != null){
//            ValueMap properties = currentPage.getProperties();
//            if(properties.containsKey("productId")){
//                productId = properties.get("productId",String.class);
//            }
//        }
//        return productId;
//    }


}
