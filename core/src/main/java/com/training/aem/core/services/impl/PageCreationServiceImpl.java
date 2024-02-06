package com.training.aem.core.services.impl;


import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.WCMException;
import com.training.aem.core.models.ProductDetailsEntity;
import com.training.aem.core.services.PageCreationService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.*;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@Component(service = PageCreationService.class)
public class PageCreationServiceImpl implements PageCreationService{
    @SlingObject
    SlingHttpServletRequest request;

    @Reference
    ResourceResolverFactory resourceResolverFactory;
    @Override
    public void createPages(ProductDetailsEntity data) throws PersistenceException {
        String parentPath = "/content/training-project/us";
        String templatePath = "/conf/training-project/settings/wcm/templates/page-content";
        createPage(parentPath,"page1",templatePath, null);


    }

    private void createPage(String parentPath, String pageName, String templatePath,ResourceResolver resourceResolver) throws PersistenceException {

        final Logger logger = LoggerFactory.getLogger(PageCreationService.class);

        try{
            Map<String,Object> param = new HashMap<>();
            param.put(resourceResolverFactory.SUBSERVICE,"rahul");
            resourceResolver = resourceResolverFactory.getServiceResourceResolver(param);

            Resource parentResource = resourceResolver.getResource(parentPath);
            if(parentResource == null){
                logger.error("Parent path does not exist");
            }
            String pagePath = parentPath + "/" + pageName;

            PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
            Page newPage = pageManager.create(parentPath,pageName,templatePath,"page 1 created",true);

        } catch (WCMException | LoginException e) {
            throw new RuntimeException(e);
        }
        finally {
            resourceResolver.commit();
        }


    }

}
