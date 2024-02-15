package com.training.aem.core.servlets;


import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = {Servlet.class},
        property = {
                "sling.servlet.paths="+"/bin/my-servlet",
                "sling.servlet.methods=" + HttpConstants.METHOD_GET
        })
public class TempServlet extends SlingAllMethodsServlet {
    @Override
    protected void doGet(SlingHttpServletRequest request,SlingHttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write("servlet is called");
    }
}
