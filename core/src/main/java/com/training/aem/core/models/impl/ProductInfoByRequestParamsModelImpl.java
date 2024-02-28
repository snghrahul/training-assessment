package com.training.aem.core.models.impl;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.training.aem.core.bean.ProductDetailsEntity;
import com.training.aem.core.models.ProductInfoByRequestParamsModel;
import com.training.aem.core.services.ProductInfoService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.request.RequestPathInfo;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;

import javax.annotation.PostConstruct;

@Model(adaptables = {SlingHttpServletRequest.class},
        adapters = {ProductInfoByRequestParamsModel.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ProductInfoByRequestParamsModelImpl  implements ProductInfoByRequestParamsModel{
    @OSGiService
    private ProductInfoService productInfoService;
    @ScriptVariable
    SlingHttpServletRequest request;

    private ProductDetailsEntity productDetails;

    @PostConstruct
    protected void init() throws Exception {

        RequestPathInfo pathInfo = request.getRequestPathInfo();
        String urlSuffix = pathInfo.getSuffix();
        String productId = urlSuffix.substring(1);
        productDetails = productInfoService.getProductInfo(productId);
    }
    public ProductDetailsEntity getProductDetails() {
        return productDetails;
    }
}
