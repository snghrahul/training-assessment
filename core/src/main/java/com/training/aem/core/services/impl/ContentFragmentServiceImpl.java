package com.training.aem.core.services.impl;

import com.training.aem.core.services.ContentFragmentService;
import org.osgi.service.component.annotations.Component;

@Component(service = {ContentFragmentService.class})
public class ContentFragmentServiceImpl implements ContentFragmentService{
    @Override
    public String getContentFragmentData(String fragmentPath) {


        return null;
    }
}
