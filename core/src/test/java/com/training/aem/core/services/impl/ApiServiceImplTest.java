package com.training.aem.core.services.impl;

import com.training.aem.core.bean.ProductDetailsEntity;
import com.training.aem.core.services.PageCreationService;
import com.training.aem.core.services.ProductService;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class ApiServiceImplTest {
    AemContext aemContext = new AemContext();
    @Mock
    PageCreationService pageCreationService;
    @Mock
    ProductServiceImpl productService;
    @InjectMocks
    @Spy
    ApiServiceImpl apiService;


    @BeforeEach
    void setUp() {
        aemContext.addModelsForClasses(ApiServiceImpl.class);
    }

    @Test
    void fetchDataAndCreatePages() throws Exception {
        ProductDetailsEntity productDetails = new ProductDetailsEntity();
        productDetails.setId(1);
        productDetails.setPrice(900);
        productDetails.setCategory("clothing");
        productDetails.setImage("google.com");
        when(productService.getFakeApiData(eq("abc.com"))).thenReturn(productDetails);
        //doNothing().when(pageCreationService.createPages(productDetails));
        apiService.fetchDataAndCreatePages();


    }
}