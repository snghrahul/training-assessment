package com.training.aem.core.models.impl;

import com.training.aem.core.bean.ProductDetailsEntity;
import com.training.aem.core.services.SortedProductsService;
import com.training.aem.core.services.impl.SortedProductServiceImpl;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class SortedProductModelByPriceImplTest {

    AemContext aemContext;

    @Mock
    SortedProductServiceImpl sortedProductService;
    @InjectMocks
    @Spy
    SortedProductModelByPriceImpl sortedProductModelByPrice;


    @BeforeEach
    void setUp() {
        aemContext.addModelsForClasses(SortedProductServiceImpl.class);
    }

    @Test
    void getSortedProductDetailsByPrice() {
        sortedProductModelByPrice.getSortedProductDetailsByPrice();
    }
}