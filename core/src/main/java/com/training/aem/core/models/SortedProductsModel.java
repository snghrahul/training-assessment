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
    private String sortType;

    List<ProductDetailsEntity> sortedProductDetails = new ArrayList<>();

    public List<ProductDetailsEntity> getSortedProductDetails(){
        sortedProductDetails = sortedProductsService.getSortedProducts(sortType);
        return sortedProductDetails;
    }


}
