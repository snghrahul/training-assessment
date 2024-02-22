package com.training.aem.core.models;

import com.training.aem.core.bean.ProductDetailsEntity;

/**
 * This interface represents a model for fetching product details by product ID.
 * Implementations of this interface should provide functionality to retrieve
 * product details from a third-party API based on the given product ID.
 */
public interface ProductDetailsByIdModel {

    /**
     * Retrieves and returns product details based on the provided product ID.
     * The product details typically include information such as product image,
     * price, category, etc.
     *
     * @return A {@link ProductDetailsEntity} object containing the details of the product.
     *         Returns null if the product details cannot be retrieved.
     */
    ProductDetailsEntity getProductDetails();
}
