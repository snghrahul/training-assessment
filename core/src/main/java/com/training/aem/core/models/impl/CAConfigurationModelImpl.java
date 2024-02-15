package com.training.aem.core.models.impl;

import com.training.aem.core.models.CAConfigurationModel;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

@Model(adaptables = {SlingHttpServletRequest.class},
              adapters ={CAConfigurationModel.class},
              defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL

)
public class CAConfigurationModelImpl {

    @SlingObject
    ResourceResolver resourceResolver;
}
