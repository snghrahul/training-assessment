package com.training.aem.core.services.impl;

import com.adobe.cq.dam.cfm.ContentFragment;
import com.training.aem.core.Constant.CommonConstant;
import com.training.aem.core.bean.AlertContentFragmentEntity;
import com.training.aem.core.services.ContentFragmentService;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.*;

@Component(service = {ContentFragmentService.class})
public class ContentFragmentServiceImpl implements ContentFragmentService{

    @Reference
    ResourceResolverFactory resourceResolverFactory;
    @Override
    public List<AlertContentFragmentEntity> getContentFragmentData() throws LoginException {
        List<AlertContentFragmentEntity> contentFragmentList = new ArrayList<>();

        ResourceResolver resourceResolver = getResourceResolver();
        Resource resource = resourceResolver.getResource(CommonConstant.CONTENT_FRAGMENT_PARENT_PATH);
        if(resource != null){
            Iterator<Resource> allChildResource = resource.listChildren();
            while (allChildResource.hasNext()){
                Resource childResource = allChildResource.next();
                if(childResource.isResourceType("dam:Asset")){
                    ContentFragment contentFragment = childResource.adaptTo(ContentFragment.class);
                    if(contentFragment == null) return null;
                    AlertContentFragmentEntity entityCF = new AlertContentFragmentEntity();
                    entityCF.setTitle(contentFragment.getElement("title").getContent());
                    entityCF.setMessage(contentFragment.getElement("message").getContent());
                    contentFragmentList.add(entityCF);
                }else {
                    continue;
                }
            }
        }
        return contentFragmentList;
    }
    public ResourceResolver getResourceResolver() throws LoginException {
        Map<String, Object> map  = new HashMap<>();
        map.put(ResourceResolverFactory.SUBSERVICE,"rahul");
        return resourceResolverFactory.getServiceResourceResolver(map);
    }

}
