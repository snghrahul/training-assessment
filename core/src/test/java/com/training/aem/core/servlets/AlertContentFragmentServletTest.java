package com.training.aem.core.servlets;


import com.training.aem.core.bean.AlertContentFragmentEntity;
import com.training.aem.core.services.impl.ContentFragmentServiceImpl;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletRequest;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(AemContextExtension.class)
class AlertContentFragmentServletTest {
    AemContext aemContext = new AemContext();

    AlertContentFragmentServlet unitTest;

    MockSlingHttpServletRequest request;

    MockSlingHttpServletResponse response;
//    @Mock
    ContentFragmentServiceImpl contentFragmentService;


    @BeforeEach
    void setUp() {


    }

    @Test
    void doGet() throws ServletException, IOException {
//        String expectedJson = "[{\"title\":\"Title 1\",\"message\":\"Message 1\"},{\"title\":\"Title 2\",\"message\":\"Message 2\"}]";
////        String path = "us/en/abc.com";
//        List<AlertContentFragmentEntity> contents = new ArrayList<>();
//        when(contentFragmentService.getContentFragmentData()).thenReturn(contents);
//        //Gson gson = new Gson();
////        when(gson.toJson(contents)).thenReturn(expectedJson);
////        verify(response).getWriter().write(expectedJson);
        request = aemContext.request();
        response = aemContext.response();
        aemContext.registerService(ContentFragmentServiceImpl.class, contentFragmentService);
        unitTest.doGet(request,response);



    }
}