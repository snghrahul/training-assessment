package com.training.aem.core.services;

import com.training.aem.core.bean.AlertContentFragmentEntity;
import org.apache.sling.api.resource.LoginException;

import java.util.List;

/**
 * Interface for retrieving all content fragments.
 * Implementations of this interface provide methods to access content fragments
 * stored within a content repository.
 */
public interface ContentFragmentService {

    /**
     * Retrieves all content fragments from the content repository.
     *
     * @return A list of {@code AlertContentFragmentEntity} objects representing
     *         all content fragments stored in the repository.
     * @throws LoginException If authentication fails while accessing the content repository.
     */
    List<AlertContentFragmentEntity> getContentFragmentData() throws LoginException;
}
