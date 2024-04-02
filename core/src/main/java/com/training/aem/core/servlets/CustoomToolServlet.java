package com.training.aem.core.servlets;


import com.google.gson.Gson;
import com.training.aem.core.bean.LocaleEntity;
import com.training.aem.core.services.CustomToolService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Component(service = {Servlet.class},
        property = {
                "sling.servlet.paths=" + "/bin/getorphanassets",
                "sling.servlet.methods=" + HttpConstants.METHOD_GET
        })
public class CustoomToolServlet extends SlingSafeMethodsServlet {
        @Reference
        CustomToolService customToolService;
        private static final Logger logger = LoggerFactory.getLogger(CustoomToolServlet.class);

        @Override
        protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
                List<String> childPageList = new ArrayList<>();
                String path = "/content/";
                String projectName = request.getParameter("selectedProject");
                path += projectName;
                try {
                        childPageList = customToolService.getAllOrphanAsset(path);
                } catch (LoginException e) {
                        throw new RuntimeException(e);
                }
                LocaleEntity localeEntity = new LocaleEntity(projectName,childPageList);
                String jsonResponse = new Gson().toJson(localeEntity);
                response.setContentType("application/json");
                response.getWriter().write(jsonResponse);
        }
}
