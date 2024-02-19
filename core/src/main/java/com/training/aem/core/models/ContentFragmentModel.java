package com.training.aem.core.models;

import com.training.aem.core.bean.AlertContentFragmentEntity;
import com.training.aem.core.services.ContentFragmentService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.osgi.service.component.annotations.Reference;

import java.util.ArrayList;
import java.util.List;

@Model(adaptables = {SlingHttpServletRequest.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
    public class ContentFragmentModel {

    @Reference
    ContentFragmentService contentFragmentService;

    List<AlertContentFragmentEntity> contentFragmentList = new ArrayList<>();

    public List<AlertContentFragmentEntity> getAllContentFragment() throws LoginException {
        contentFragmentList = contentFragmentService.getContentFragmentData();
        return contentFragmentList;
    }
}
