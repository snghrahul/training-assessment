package com.training.aem.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.ServletResolverConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Iterator;

@Component(service = {Servlet.class})
@SlingServletResourceTypes(
        resourceTypes = "training-project/components/fetch-products-by-filters",
        methods = HttpConstants.METHOD_GET
)
public class ResourceTypeServlet extends SlingSafeMethodsServlet {
    private static final Logger logger = LoggerFactory.getLogger(ResourceTypeServlet.class);
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
//        response.getWriter().write("called");
//        StringBuilder result = new StringBuilder();
//        String parentPath = "/content/training-project";
//        ResourceResolver resourceResolver = request.getResourceResolver();
//       Resource resource = resourceResolver.getResource(parentPath);
//       String title = resource.getValueMap().get("jcr:title", String.class);

       response.getWriter().write(String.valueOf("title"));
        logger.info("Servlet code started................!!!");




    }
}
