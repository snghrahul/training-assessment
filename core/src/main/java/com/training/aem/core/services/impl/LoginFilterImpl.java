package com.training.aem.core.services.impl;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.osgi.service.component.annotations.Component;

import javax.servlet.*;
import java.io.IOException;

@Component(service = {Filter.class})
public class LoginFilterImpl implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        SlingHttpServletRequest httpRequest = (SlingHttpServletRequest) request;
        String username = httpRequest.getParameter("username");
        String password = httpRequest.getParameter("password");
        if(isValidCredentials(username,password)){
            response.getWriter().write(username);
            chain.doFilter(request,response);
        }else {
            SlingHttpServletResponse httpResponse = (SlingHttpServletResponse) response;
            response.getWriter().write("not authorized user");
            httpResponse.setStatus(SlingHttpServletResponse.SC_FORBIDDEN);
        }

    }
    private boolean isValidCredentials(String username, String password) {
        return "admin".equals(username) && "password123".equals(password);
    }

    @Override
    public void destroy() {

    }
}
