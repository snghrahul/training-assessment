package com.training.aem.core.models.impl;

import com.training.aem.core.models.CustomToolPages;
import com.training.aem.core.services.CustomToolService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import java.util.ArrayList;
import java.util.List;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = {CustomToolPages.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL

)
public class CustomToolPagesImpl implements CustomToolPages {
    @OSGiService
    CustomToolService customToolService;

    List<String> pagesList = new ArrayList<>();
    @Override
    public List<String> getPagesList() throws LoginException {
        pagesList = customToolService.getAllOrphanAsset("/content");
        return pagesList;
    }
}
