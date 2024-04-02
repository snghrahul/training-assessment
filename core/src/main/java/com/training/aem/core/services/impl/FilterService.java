package com.training.aem.core.services.impl;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.engine.EngineConstants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.osgi.service.component.propertytypes.ServiceRanking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

@Component(service = Filter.class,
        property = {
                EngineConstants.SLING_FILTER_SCOPE + "=" + EngineConstants.FILTER_SCOPE_COMPONENT,
                EngineConstants.SLING_FILTER_RESOURCETYPES+"=wknd/components/text"
        }

)
@ServiceDescription("Filter to check the performance of a component")
@ServiceRanking(12)
public class FilterService implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(FilterService.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final SlingHttpServletRequest slingRequest = (SlingHttpServletRequest) request;
        long start = System.currentTimeMillis();
        logger.info("called before actual servlet");
        chain.doFilter(request,response);

        long end = System.currentTimeMillis();
        logger.info("Time taken to render the component: " +((SlingHttpServletRequest) request).getResource().getPath()+" is " + (end - start) + "ms");


    }

    @Override
    public void destroy() {

    }
}
