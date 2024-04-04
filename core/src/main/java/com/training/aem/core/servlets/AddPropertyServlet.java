package com.training.aem.core.servlets;

import com.training.aem.core.services.NodeCreationService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.*;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.settings.SlingSettingsService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;


@Component(service = {Servlet.class},property = {
        "sling.servlet.paths=" + "/bin/add",
        "sling.servlet.methds=" + HttpConstants.METHOD_GET
})
public class AddPropertyServlet extends SlingSafeMethodsServlet {

    @Reference
    NodeCreationService nodeCreationService;

    @Reference
    SlingSettingsService slingSettingsService;

    private static final Logger log = LoggerFactory.getLogger(AddPropertyServlet.class);
    @Override
    protected void doGet(SlingHttpServletRequest request,SlingHttpServletResponse response) throws ServletException, IOException {

        if(slingSettingsService.getRunModes().contains("author")){
            String jcrPath = request.getParameter("jcrPath");
            if (jcrPath == null || jcrPath.isEmpty()) {
                response.setStatus(SlingHttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Invalid request parameters");
                log.debug("Empty path");
                return;
            }
            ResourceResolver resourceResolver = request.getResourceResolver();
            Resource resource = resourceResolver.getResource(jcrPath);
            if (ResourceUtil.isNonExistingResource(resource)) {
                response.setStatus(SlingHttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("Resource not found at path: " + jcrPath);
                log.debug("Wrong Resource");
                return;
            }
            ModifiableValueMap valueMap = resource.adaptTo(ModifiableValueMap.class);
            if (valueMap == null) {
                response.setStatus(SlingHttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Failed to adapt Resource to ModifiableValueMap");
                log.debug("Failed to adapt Resource to ModifiableValueMap");
                return;
            }
            valueMap.put("cq:lastReplicationAction", "Activate");
            resourceResolver.commit();
            log.debug("Property Updated successfully");
            response.getWriter().write("Property Updated successfully");
        }else{
            response.setStatus(SlingHttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("This servlet can only be accessed in author run mode");
            log.debug("Forbidden access. This servlet can only be accessed in author run mode");
        }
    }

}
