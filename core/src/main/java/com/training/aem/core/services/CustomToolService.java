package com.training.aem.core.services;

import org.apache.sling.api.resource.LoginException;

import java.util.List;

public interface CustomToolService {
    List<String> getAllOrphanAsset(String path) throws LoginException;
}
