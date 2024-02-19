package com.training.aem.core.models;

import com.training.aem.core.bean.ProductDetailsEntity;

/**
 * This interface represents a model for retrieving product details based on request parameters.
 * <p>
 * Implementations of this interface should provide functionality to fetch product details
 * based on the parameters provided in the request.
 */
public interface ProductInfoByRequestParamsModel {
    /**
     * Retrieves product details based on the request parameters.
     *
     * @return A {@link ProductDetailsEntity} object representing the details of the product
     *         based on the request parameters. Returns null if the product details cannot be retrieved
     *         or if there is an error.
     * @throws RuntimeException if an error occurs during the retrieval process.
     */
    ProductDetailsEntity getProductDetails();
}
