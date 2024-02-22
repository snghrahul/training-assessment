package com.training.aem.core.services;

import com.training.aem.core.bean.ProductDetailsEntity;

import java.util.List;

/**
 * This interface defines a service for retrieving and sorting products based on specified criteria.
 */
public interface SortedProductsService {
    /**
     * Retrieves a list of product details entities sorted based on the specified sort type.
     *
     * @param sortType The type of sorting to apply (e.g., "price", "id", etc.).
     * @return A {@link List} of {@link ProductDetailsEntity} objects representing the sorted products.
     *         Returns an empty list if no products are found or if there is an error.
     * @throws IllegalArgumentException if the specified sort type is invalid or unsupported.
     * @throws RuntimeException if an error occurs during the retrieval or sorting process.
     */
    List<ProductDetailsEntity> getSortedProducts(String sortType);
//    List<ProductDetailsEntity> getAllProducts(String url);
}
