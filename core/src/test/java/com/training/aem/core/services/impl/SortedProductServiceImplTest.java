//package com.training.aem.core.services.impl;
//
//import io.wcm.testing.mock.aem.junit5.AemContext;
//import io.wcm.testing.mock.aem.junit5.AemContextExtension;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.StatusLine;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//@ExtendWith({AemContextExtension.class, MockitoExtension.class})
//class SortedProductServiceImplTest {
//    AemContext aemContext;
//    @Mock
//    CloseableHttpClient httpClient;
//    @InjectMocks
//    SortedProductServiceImpl sortedProductService;
//    @Mock
//    HttpResponse httpResponse;
//    @Mock
//    HttpEntity httpEntity;
//    @Mock
//    StatusLine statusLine;
//    @Mock
//    HttpClients httpClients;
//    @BeforeEach
//    void setUp() {
//        aemContext.addModelsForClasses(SortedProductServiceImpl.class);
//    }
//
//    @Test
//    void getAllProducts() {
//        when(httpClients.createDefault()).thenReturn(httpClient);
//        sortedProductService.getAllProducts("google.com");
//
//    }
//
//    @Test
//    void getSortedProducts() {
//
//    }
//}