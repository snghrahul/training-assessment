package com.training.aem.core.models;


import com.training.aem.core.models.ProductDetailsEntity;
import com.training.aem.core.services.ProductInfoService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.osgi.service.component.annotations.Reference;

import javax.annotation.PostConstruct;

@Model(adaptables = {SlingHttpServletRequest.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ProductInfoModel {

    @OSGiService
    private ProductInfoService productInfoService;


    @Reference
    SlingHttpServletRequest request;

    private ProductDetailsEntity productDetails;

    @PostConstruct
    protected void init() throws Exception {
       String productId = request.getParameter("productId");
        productDetails = productInfoService.getProductInfo(1);
    }
    public ProductDetailsEntity getProductDetails() {
        return productDetails;
    }


}
