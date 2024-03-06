package com.training.aem.core.services.impl;

import com.training.aem.core.bean.ProductDetailsEntity;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class ProductDetailsEntityComparatorTest {
    AemContext aemContext = new AemContext();

    @BeforeEach
    void setUp() {
        aemContext.addModelsForClasses(ProductDetailsEntityComparator.class);
    }
    @Test
    void compare() {
        ProductDetailsEntityComparator comparator = new ProductDetailsEntityComparator("ascending");
        ProductDetailsEntityComparator comparator2 = new ProductDetailsEntityComparator("mostRecent");
        ProductDetailsEntityComparator comparator3 = new ProductDetailsEntityComparator("descending");
        ProductDetailsEntity product1 = new ProductDetailsEntity(1, 10.0,"image","category");
        ProductDetailsEntity product2 = new ProductDetailsEntity(2, 20.0,"image","category");
        int result = comparator.compare(product1,product2);
        int result2 = comparator2.compare(product1,product2);
        int result3 = comparator3.compare(product1,product2);
//        when("ascending".equalsIgnoreCase(sortType)).thenReturn(Double.compare(product1.getPrice(),product2.getPrice()));
        assertEquals(-1,result);
        assertEquals(1,result2);
        assertEquals(1,result3);
    }
}