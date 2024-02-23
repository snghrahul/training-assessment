package com.training.aem.core.models.impl;

import com.training.aem.core.bean.ProductDetailsEntity;
import com.training.aem.core.models.SortedProductModelByPrice;
import com.training.aem.core.services.SortedProductsService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.util.ArrayList;
import java.util.List;

@Model(adaptables = {SlingHttpServletRequest.class},
        adapters = {SortedProductModelByPrice.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SortedProductModelByPriceImpl implements SortedProductModelByPrice{

    @OSGiService
    SortedProductsService sortedProductsService;
    @ValueMapValue
    private String sortType;

    List<ProductDetailsEntity> sortedProductList = new ArrayList<>();

    public List<ProductDetailsEntity> getSortedProductDetailsByPrice(){
        return sortedProductsService.getSortedProducts(sortType);
    }
}
