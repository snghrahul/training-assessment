package com.training.aem.core.services;

import com.training.aem.core.models.ProductDetailsEntity;
import org.apache.sling.api.resource.PersistenceException;

public interface PageCreationService {
    void createPages(ProductDetailsEntity data) throws PersistenceException;
}
