package com.training.aem.core.servlets;

import com.adobe.cq.dam.cfm.ContentFragmentManager;
import com.adobe.cq.wcm.core.components.models.contentfragment.ContentFragmentList;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.training.aem.core.Constant.CommonConstant;
import com.training.aem.core.bean.AlertContentFragmentEntity;
import com.training.aem.core.services.ContentFragmentService;
import org.apache.http.HttpStatus;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

@Component(service = {Servlet.class},property = {
        CommonConstant.SLING_SERVLET_PATH + "/bin/getAllContentFragments",
        CommonConstant.SLING_SERVLET_METHOD + HttpConstants.METHOD_GET,
        CommonConstant.SLING_SERVLET_METHOD + HttpConstants.METHOD_POST
})
public class AlertContentFragmentServlet extends SlingAllMethodsServlet {
    @Reference
    ContentFragmentService contentFragmentService;
    private static final Logger logger = LoggerFactory.getLogger(AlertContentFragmentServlet.class);
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        try {

            List<AlertContentFragmentEntity> allContentFragments = contentFragmentService.getContentFragmentData();
            Gson gson = new Gson();
            String json = gson.toJson(allContentFragments);
            response.getWriter().write(json);
        } catch (LoginException e) {
            logger.error("Unable to obtain resource resolver : {}" + e.getMessage());
            response.setStatus(HttpStatus.SC_BAD_REQUEST);
        }
    }
}
