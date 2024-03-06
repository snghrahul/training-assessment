package com.training.aem.core.services.impl;

import com.training.aem.core.bean.ExcelRowDataEntity;
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
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.jcr.Node;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class NodeCreationServiceImplTest {
    AemContext aemContext;
    @Mock
    ResourceResolver resourceResolver;
    @Mock
    ResourceResolverFactory resourceResolverFactory;
    @InjectMocks
    @Spy
    NodeCreationServiceImpl nodeCreationService;
    @Mock
    Resource resource;

    @BeforeEach
    void setUp() {
        aemContext = new AemContext();
    }

    @Test
    void createNodeFromExcel() throws LoginException {
        Node parentNode  = mock(Node.class);
        ExcelRowDataEntity excelRowDataEntity = new ExcelRowDataEntity();
        excelRowDataEntity.setColumn1("columnName2");
        excelRowDataEntity.setColumn2(882);
        List<ExcelRowDataEntity> rowDataEntityList= new ArrayList<>();
        rowDataEntityList.add(excelRowDataEntity);
       // rowDataEntityList.add(new ExcelRowDataEntity("columnName2",789));
        Map<String, Object> map  = new HashMap<>();
        map.put(ResourceResolverFactory.SUBSERVICE,"rahul");
        when(resourceResolverFactory.getServiceResourceResolver(map)).thenReturn(resourceResolver);
        aemContext.registerService(ResourceResolverFactory.class,resourceResolverFactory);
        when(resourceResolver.getResource("/content/training-project")).thenReturn(resource);
        when(resource.adaptTo(Node.class)).thenReturn(parentNode);
        nodeCreationService.CreateNodeFromExcel(rowDataEntityList);
    }

    @Test
    void getResourceResolver() {
    }
}