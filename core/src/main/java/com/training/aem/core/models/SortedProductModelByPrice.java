package com.training.aem.core.models;

import com.training.aem.core.bean.ProductDetailsEntity;
import java.util.List;

public interface SortedProductModelByPrice {
    List<ProductDetailsEntity> getSortedProductDetailsByPrice();
}
