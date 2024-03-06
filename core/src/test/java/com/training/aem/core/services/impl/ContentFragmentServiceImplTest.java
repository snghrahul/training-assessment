package com.training.aem.core.services.impl;

import com.adobe.cq.dam.cfm.ContentFragment;
import com.training.aem.core.bean.AlertContentFragmentEntity;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class ContentFragmentServiceImplTest {
    AemContext aemContext = new AemContext();
    @InjectMocks
    ContentFragmentServiceImpl contentFragmentService;
    @Mock
    ResourceResolverFactory resourceResolverFactory;
    @Mock
    ResourceResolver resourceResolver;
    @Mock
    Resource resource;
    @Mock
    Resource childResource;
    @Mock
    Iterator<Resource> allchildResource;
    @Mock
    ContentFragment contentFragment;

    @BeforeEach
    void setUp() {
        aemContext.addModelsForClasses(ContentFragmentServiceImpl.class);
    }

    @Test
    void getContentFragmentData() throws LoginException {
        AlertContentFragmentEntity entityCF = new AlertContentFragmentEntity();
        Map<String, Object> map  = new HashMap<>();
        map.put(ResourceResolverFactory.SUBSERVICE,"rahul");
        when(resourceResolverFactory.getServiceResourceResolver(map)).thenReturn(resourceResolver);
        when(resourceResolver.getResource("/content/dam/training-project/content-fragment")).thenReturn(resource);
        when(resource.listChildren()).thenReturn(allchildResource);
        when(allchildResource.next()).thenReturn(childResource);
        when(childResource.adaptTo(ContentFragment.class)).thenReturn(contentFragment);
        entityCF.setTitle("title");
        entityCF.setMessage("message");


        contentFragmentService.getContentFragmentData();

    }

    @Test
    void getResourceResolver() {
    }
}