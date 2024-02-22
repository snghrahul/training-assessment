package com.training.aem.core.models.impl;

import com.adobe.cq.commerce.common.ValueMapDecorator;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.training.aem.core.bean.ProductDetailsEntity;
import com.training.aem.core.models.ProductDetailsByIdModel;
import com.training.aem.core.services.ProductService;
import com.training.aem.core.services.impl.ProductServiceImpl;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class ProductsDetailsByIdModelImplTest {

    AemContext aemContext = new AemContext();

    @Mock
    private SlingHttpServletRequest request;

    @Mock
    private Resource resource;

    @Mock
    private ResourceResolver resourceResolver;

    @Mock
    PageManager pageManager;

    @Mock
    private Page currentPage;

    @Mock
     private ProductService productService;


    @InjectMocks
    private ProductsDetailsByIdModelImpl productsDetailsByIdModel;


    @BeforeEach
    void setUp() {
        aemContext.addModelsForClasses(ProductsDetailsByIdModelImpl.class);
    }

    @Test
    void init() throws Exception {

        when(request.getResource()).thenReturn(resource);
        when(resource.getResourceResolver()).thenReturn(resourceResolver);
        when(resourceResolver.adaptTo(PageManager.class)).thenReturn(pageManager);
        when(pageManager.getContainingPage(resource)).thenReturn(currentPage);
        Map<String,Object> map = new HashMap<>();
        map.put("productId","1");
        ValueMapDecorator valuemap = new ValueMapDecorator(map);
        when(currentPage.getProperties()).thenReturn(valuemap);

        ProductDetailsEntity productDetails = new ProductDetailsEntity();
        when(productService.getFakeApiData("https://fakestoreapi.com/products/" + "1")).thenReturn(productDetails);
        productsDetailsByIdModel.init();


    }
}