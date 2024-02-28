package com.training.aem.core.servlets;

import com.adobe.cq.dam.cfm.ContentFragmentManager;
import com.adobe.cq.wcm.core.components.models.contentfragment.ContentFragmentList;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.training.aem.core.Constant.CommonConstant;
import com.training.aem.core.bean.AlertContentFragmentEntity;
import com.training.aem.core.services.ContentFragmentService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

@Component(service = {Servlet.class},property = {
        CommonConstant.SLING_SERVLET_PATH + "/bin/getAllContentFragments",
        CommonConstant.SLING_SERVLET_METHOD + HttpConstants.METHOD_GET
})
public class AlertContentFragmentServlet extends SlingSafeMethodsServlet {
    @Reference
    ContentFragmentService contentFragmentService;
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        try {
            List<AlertContentFragmentEntity> contents = contentFragmentService.getContentFragmentData();
            Gson gson = new Gson();
            String json = gson.toJson(contents);
            response.getWriter().write(json);
        } catch (LoginException e) {
            String errorMessage;
            errorMessage = "Unable to obtain resource resolver" + e.getMessage();
            throw new RuntimeException(errorMessage,e);
        }
    }
}
