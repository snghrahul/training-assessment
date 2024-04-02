package com.training.aem.core.servlets;

import com.google.gson.Gson;
import com.training.aem.core.services.OrphanAssets;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.jcr.RepositoryException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Map;

@Component(service = {Servlet.class},property = {
        "sling.servlet.paths=" + "/bin/getdata",
        "sling.servlet.methds=" + HttpConstants.METHOD_GET
})
public class GetOrphanAssetsServlet extends SlingSafeMethodsServlet {
    @Reference
    OrphanAssets orphanAssets;
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        String path = "/content/dam/";
        Map<String, String> map;
        String queryParam = request.getParameter("path");

        try {
            map = orphanAssets.getAllOrphanAssets(path + queryParam);
        } catch (LoginException e) {
            throw new RuntimeException(e);
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
        String mapJsonString = new Gson().toJson(map);
        response.setContentType("application/json");
        response.getWriter().write(mapJsonString);

    }
}
