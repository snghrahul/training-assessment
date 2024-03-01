package com.training.aem.core.services.impl;

import com.training.aem.core.bean.ProductDetailsEntity;
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
import static org.mockito.Mockito.when;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class ProductInfoServiceImplTest {
    AemContext aemContext = new AemContext();
    @Mock
    ProductServiceImpl productService;
    @InjectMocks
    ProductInfoServiceImpl productInfoService;

    @BeforeEach
    void setUp() {
        aemContext.addModelsForClasses(ProductInfoServiceImpl.class);
    }

    @Test
    void getProductInfo() throws Exception {
        ProductDetailsEntity productDetails = new ProductDetailsEntity();
        productDetails.setId(1);
        productDetails.setCategory("clothing");
        productDetails.setImage("google");
        productDetails.setPrice(900);
        when(productService.getFakeApiData("https://fakestoreapi.com/products/" + "1")).thenReturn(productDetails);
        productInfoService.getProductInfo("1");
    }
}