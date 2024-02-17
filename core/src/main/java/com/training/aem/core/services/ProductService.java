package com.training.aem.core.services;

import com.training.aem.core.bean.ProductDetailsEntity;

/**
 * This interface defines the contract for a service responsible for fetching
 * product data from third-party APIs and populating product entity objects with
 * the retrieved data.
 */
public interface ProductService {
    /**
     * Retrieves product details from the specified API URL and populates a
     * {@link ProductDetailsEntity} object with the fetched data.
     *
     * @param apiUrl The URL of the third-party API from which to fetch the product details.
     * @return A {@link ProductDetailsEntity} object containing the details of the product.
     *         Returns null if the product details cannot be retrieved or if there is an error.
     * @throws RuntimeException if an error occurs during the API request or data processing.
     */
    ProductDetailsEntity getFakeApiData(String apiUrl) throws Exception;


}
