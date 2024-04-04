package com.training.aem.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = {Servlet.class},property = {
        "sling.servlet.paths=" + "/bin/remove",
        "sling.servlet.methds=" + HttpConstants.METHOD_POST
})
public class DeleteAssetsServlet extends SlingAllMethodsServlet {

    private static final Logger logger = LoggerFactory.getLogger(DeleteAssetsServlet.class);
    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        String path = "/content/dam/";
        String assetPath = request.getParameter("path");

        ResourceResolver resourceResolver = request.getResourceResolver();
        Session session = resourceResolver.adaptTo(Session.class);
        if(session != null){
            try {
                Node assetNode = session.getNode(path + assetPath);
                NodeIterator childNodes = assetNode.getNodes();
                while (childNodes.hasNext()){
                    Node currentChildNode = childNodes.nextNode();
                    currentChildNode.remove();
                }
                session.save();
            } catch (RepositoryException e) {
                throw new RuntimeException(e);
            }
        }else {
            logger.error("ResourceResolver could not be adapted to a Session");
            response.getWriter().write("failed to delete asset");

        }
    }
}
