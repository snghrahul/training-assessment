package com.training.aem.core.services.impl;


import com.day.cq.replication.ReplicationActionType;
import com.day.cq.replication.ReplicationException;
import com.day.cq.replication.Replicator;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.WCMException;
import com.training.aem.core.bean.ProductDetailsEntity;
import com.training.aem.core.services.PageCreationService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.*;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Session;
import java.util.HashMap;
import java.util.Map;

@Component(service = PageCreationService.class)
public class PageCreationServiceImpl implements PageCreationService{
    @SlingObject
    SlingHttpServletRequest request;

    @Reference
    ResourceResolverFactory resourceResolverFactory;
    @Reference
    private Replicator replicator;

    private final Logger logger = LoggerFactory.getLogger(ApiServiceImpl.class);
    @Override
    public void createPages(ProductDetailsEntity data) throws PersistenceException {
        String parentPath = "/content/training-project/us";
        String templatePath = "/conf/training-project/settings/wcm/templates/page-content";
        createPage(parentPath,"page1",templatePath, null);


    }

    private void createPage(String parentPath, String pageName, String templatePath,ResourceResolver resourceResolver) throws PersistenceException {

        final Logger logger = LoggerFactory.getLogger(PageCreationService.class);

        try{
            resourceResolver = getResourceResolver();

            Resource parentResource = resourceResolver.getResource(parentPath);
            if(parentResource == null){
                logger.error("Parent path does not exist");
            }
            String pagePath = parentPath + "/" + pageName;

            PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
            Page newPage = pageManager.create(parentPath,pageName,templatePath,"page 1 created",true);

            if(newPage != null){
                Session session = resourceResolver.adaptTo(Session.class);
                managePageActivation(session,newPage.getPath());
            }

        } catch (WCMException | LoginException e) {
            throw new RuntimeException(e);
        }
        finally {
            resourceResolver.commit();
        }
    }

    private void managePageActivation(Session session, String path){
        try{
            if (session != null){
                replicator.replicate(session, ReplicationActionType.ACTIVATE,path);
                logger.info("Page activated: ", path);
            }
        } catch (ReplicationException e) {
            throw new RuntimeException(e);
        }
    }

    public ResourceResolver getResourceResolver() throws LoginException {
        Map<String, Object> map  = new HashMap<>();
        map.put(ResourceResolverFactory.SUBSERVICE,"rahul");
        return resourceResolverFactory.getServiceResourceResolver(map);
    }

}
