package com.training.aem.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;

@Model(adaptables = {Resource.class},defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ImageOrVideoModel {

    @ValueMapValue
    String video;

    @ValueMapValue
    String assetType;

    @ValueMapValue
    private String image;

    public String getImage() {
        return image;
    }
    public String getAssetType() {
        return assetType;
    }

    public String getVideo() {
        return video;
    }



}
