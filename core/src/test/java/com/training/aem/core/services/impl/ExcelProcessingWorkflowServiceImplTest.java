package com.training.aem.core.services.impl;

import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.Rendition;
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

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class ExcelProcessingWorkflowServiceImplTest {
    AemContext aemContext = new AemContext();
    @Mock
    NodeCreationServiceImpl nodeCreationService;
    @InjectMocks
    ExcelProcessingWorkflowServiceImpl excelProcessingWorkflowService;
    @Mock
    InputStream stream;
    @Mock
    ResourceResolverFactory resourceResolverFactory;
    @Mock
    ResourceResolver resourceResolver;
    @Mock
    Resource resource;
    @Mock
    Asset asset;
    @Mock
    Rendition rendition;

    @BeforeEach
    void setUp() {
    }

    @Test
    void processExcelFile() throws LoginException {
        String filePath = "/content/dam/training-project/temp.xlsx";
        Map<String, Object> map  = new HashMap<>();
        map.put(ResourceResolverFactory.SUBSERVICE,"rahul");
        when(resourceResolverFactory.getServiceResourceResolver(map)).thenReturn(resourceResolver);
        when(resourceResolver.getResource(filePath)).thenReturn(resource);
        when(resource.adaptTo(Asset.class)).thenReturn(asset);
        when(asset.getOriginal()).thenReturn(rendition);
        when(rendition.getStream()).thenReturn(stream);
        excelProcessingWorkflowService.processExcelFile("/content/dam/training-project/temp.xlsx");


    }
}