package com.training.aem.core.servlets;

import com.training.aem.core.services.impl.LoginFilterImpl;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.engine.EngineConstants;
import org.osgi.service.component.annotations.Component;

import javax.servlet.*;
import java.io.IOException;

@Component(service = {Servlet.class},
        property = {
                "sling.servlet.paths=" + "/bin/add",
                "sling.servlet.methds=" + HttpConstants.METHOD_GET
        }
)
public class SlingFilterServlet extends SlingAllMethodsServlet {
    LoginFilterImpl loginFilter;
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {


    }
}
