package com.training.aem.core.services;

import org.apache.sling.api.resource.LoginException;

import javax.jcr.RepositoryException;
import java.util.List;
import java.util.Map;

public interface OrphanAssets {
    Map<String,String> getAllOrphanAssets(String path) throws LoginException, RepositoryException;
}
