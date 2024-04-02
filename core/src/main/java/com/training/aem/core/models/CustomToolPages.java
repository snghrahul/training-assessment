package com.training.aem.core.models;

import org.apache.sling.api.resource.LoginException;

import java.util.List;

public interface CustomToolPages {
   List<String> getPagesList() throws LoginException;

}
