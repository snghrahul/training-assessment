package com.training.aem.core.models.impl;

import com.adobe.cq.wcm.core.components.commons.link.Link;
import com.adobe.cq.wcm.core.components.models.ListItem;
import com.adobe.cq.wcm.core.components.models.Teaser;
import com.drew.lang.annotations.Nullable;
import com.training.aem.core.models.TeaserCustom;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.via.ResourceSuperType;

import java.util.List;

@Model(adaptables = {Resource.class,SlingHttpServletRequest.class},
        adapters = {TeaserCustom.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        resourceType = "training-project/components/teaser")
public class TeaserCustomImpl implements TeaserCustom {
    @ValueMapValue
    private String textValue;

    @Self
    @Via(type = ResourceSuperType.class)
    private Teaser teaser;

    public String getTextValue() {
        return textValue;
    }






}
