package com.training.aem.core.servlets;

import com.adobe.cq.dam.cfm.ContentFragmentManager;
import com.adobe.cq.wcm.core.components.models.contentfragment.ContentFragmentList;
import com.training.aem.core.Constant.CommonConstant;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = {Servlet.class},property = {
        CommonConstant.SLING_SERVLET_PATH + "/bin/getAllContentFragments",
        CommonConstant.SLING_SERVLET_METHOD + HttpConstants.METHOD_GET

})
public class AlertContentFragmentServlet extends SlingSafeMethodsServlet {
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        String parentPath = "/content/dam/training-project/content-fragment";
        ResourceResolver resourceResolver = request.getResourceResolver();
        ContentFragmentManager fragmentManager = resourceResolver.adaptTo(ContentFragmentManager.class);
        response.getWriter().write(parentPath);



    }
}
