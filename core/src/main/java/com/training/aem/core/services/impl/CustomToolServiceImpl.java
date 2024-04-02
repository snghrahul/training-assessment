package com.training.aem.core.services.impl;

import com.training.aem.core.services.CustomToolService;
import com.training.aem.core.services.NodeCreationService;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.ArrayList;
import java.util.List;

@Component(service = {CustomToolService.class},immediate = true)
public class CustomToolServiceImpl implements CustomToolService {
    @Reference
    NodeCreationService nodeCreationService;
    @Override
    public List<String> getAllOrphanAsset(String path) throws LoginException {
        List<String> assetList = new ArrayList<>();
//         String path = "/content";
        ResourceResolver resourceResolver = nodeCreationService.getResourceResolver();
        Resource resource = resourceResolver.getResource(path);
        for(Resource childResource : resource.getChildren()){
            if("cq:Page".equals(childResource.getResourceType())){
                assetList.add(childResource.getName());
            }
        }
        return assetList;
    }
}
