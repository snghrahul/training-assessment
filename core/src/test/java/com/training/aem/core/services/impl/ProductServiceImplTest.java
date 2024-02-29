//package com.training.aem.core.services.impl;
//
//import com.day.cq.wcm.api.PageManager;
//import com.training.aem.core.bean.ClientResponse;
//import com.training.aem.core.bean.ProductDetailsEntity;
//import com.training.aem.core.utils.CommonUtils;
//import io.wcm.testing.mock.aem.junit5.AemContextExtension;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.http.HttpEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpUriRequest;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.sling.api.SlingHttpServletRequest;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//@ExtendWith({AemContextExtension.class, MockitoExtension.class})
//class ProductServiceImplTest {
//
//    @Mock
//    SlingHttpServletRequest request;
//
//    @Mock
//    PageManager pageManager;
//
//    @InjectMocks
//    private ProductServiceImpl productService;
//    @Mock
//    CloseableHttpResponse httpResponse;
//
//    @Mock
//    CloseableHttpClient httpClient;
//    @Mock
//    HttpEntity httpEntity;
//
//    @BeforeEach
//    void setUp() {
//    }
//
//    @Test
//    void getFakeApiData() throws Exception {
//
//        ClientResponse clientResponse = new ClientResponse();
//
//        ProductDetailsEntity productDetailsEntity = new ProductDetailsEntity();
//
//        String apiUrl = "abc/jd.html";
//        String method = "GET";
//        String token = null;
//        String requestObject = null;
//        HttpGet httpGet = new HttpGet(apiUrl);
//
//
//        when(HttpClients.createDefault()).thenReturn(httpClient);
//        when(httpClient.execute(any(HttpUriRequest.class))).thenReturn(httpResponse);
//        when(httpResponse.getEntity()).thenReturn(httpEntity);
//        productService.getFakeApiData(apiUrl);
//
//
//
//    }
//}