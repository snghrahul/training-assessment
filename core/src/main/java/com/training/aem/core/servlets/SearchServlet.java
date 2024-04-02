package com.training.aem.core.servlets;

//import com.training.aem.core.services.SearchService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.jcr.RepositoryException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = {Servlet.class},
        property = {
                "sling.servlet.paths=" + "/bin/searchresult",
                "sling.servlet.methods=" + HttpConstants.METHOD_GET
        })
public class SearchServlet extends SlingSafeMethodsServlet {
//    @Reference
//    SearchService searchService;
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        String path = "/content/training-project";
        ResourceResolver resolver = request.getResourceResolver();
        Resource resource = resolver.getResource(path);
        if(resource == null){
            response.getWriter().write("resource is not found : ");
        }else {
            String pageTitle = resource.getValueMap().get("jcr:title",String.class);
            response.getWriter().write("Page Title is : " + pageTitle);
        }




    }
}
