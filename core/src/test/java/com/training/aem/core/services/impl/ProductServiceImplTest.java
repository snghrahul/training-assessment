//package com.training.aem.core.services.impl;
//
//import com.day.cq.wcm.api.PageManager;
//import com.training.aem.core.bean.ClientResponse;
//import com.training.aem.core.bean.ProductDetailsEntity;
//import com.training.aem.core.utils.CommonUtils;
//import io.wcm.testing.mock.aem.junit5.AemContext;
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
//import javax.servlet.http.HttpServletResponse;
//
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//@ExtendWith({AemContextExtension.class, MockitoExtension.class})
//class ProductServiceImplTest {
//    AemContext aemContext = new AemContext();
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
//        aemContext.addModelsForClasses(ProductServiceImpl.class);
//    }
//
//    @Test
//    void getFakeApiData() throws Exception {
//
//        ProductDetailsEntity productDetailsEntity = new ProductDetailsEntity();
//        String responseData = "{\\\"id\\\":1,\\\"image\\\":\\\"example.jpg\\\",\\\"category\\\":\\\"electronics\\\",\\\"price\\\":99.99}";
//        ClientResponse clientResponse = new ClientResponse();
//        clientResponse.setStatusCode(HttpServletResponse.SC_OK);
//        clientResponse.setData(responseData);
//
//        CommonUtils mockedCommonUtils = mock(CommonUtils.class);
//        when(mockedCommonUtils.getClientResponse(anyString(), anyString(), isNull(), isNull()))
//                .thenReturn(responseData);
//
//        String apiUrl = "http://example.com/api/product";
//        String method = "GET";
//        String token = null;
//        String requestObject = null;
//        HttpGet httpGet = new HttpGet(apiUrl);
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