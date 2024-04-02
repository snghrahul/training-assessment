package com.training.aem.core.services.impl;

import com.training.aem.core.services.ApiService;
import com.training.aem.core.services.TempServiceInterface;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;

@Component(service =TempService .class)
public class TempService implements TempServiceInterface {


    @Override
    public String fun() {
        return "null";
    }
}
