package com.training.aem.core.models;

import com.training.aem.core.services.ContentFragmentService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.osgi.service.component.annotations.Reference;

@Model(adaptables = {SlingHttpServletRequest.class},defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ContentFragmentModel {

    @Reference
    ContentFragmentService contentFragmentService;

    public String getContent(){
        String fragmentPath = "/content/dam/training-project/content-fragment/alert-component-content-fragment/jcr:content/data/master";
        return contentFragmentService.getContentFragmentData(fragmentPath);
    }
}
