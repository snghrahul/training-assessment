package com.training.aem.core.models.impl;

import com.training.aem.core.bean.ProductDetailsEntity;
import com.training.aem.core.services.ProductInfoService;
import com.training.aem.core.services.impl.ProductInfoServiceImpl;
import com.training.aem.core.services.impl.SortedProductServiceImpl;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.request.RequestPathInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class ProductInfoByRequestParamsModelImplTest {

    AemContext aemContext;
    @InjectMocks
    ProductInfoByRequestParamsModelImpl productInfoByRequestParamsModel;
    @Mock
    ProductInfoService productInfoService;

    @Mock
    SlingHttpServletRequest request;
    @Mock
    RequestPathInfo pathInfo;
    @BeforeEach
    void setUp() {
        aemContext.addModelsForClasses(ProductInfoByRequestParamsModelImpl.class);
    }

    @Test
    void init() throws Exception {
        ProductDetailsEntity productDetails = new ProductDetailsEntity();
        String urlSuffix = "heyy";
        when(request.getRequestPathInfo()).thenReturn(pathInfo);
        when(pathInfo.getSuffix()).thenReturn(urlSuffix);
        when(urlSuffix.substring(1));
        when(productInfoService.getProductInfo("1")).thenReturn(productDetails);
        productInfoByRequestParamsModel.init();



    }

    @Test
    void getProductDetails() {
    }
}