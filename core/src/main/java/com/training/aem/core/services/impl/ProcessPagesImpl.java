package com.training.aem.core.services.impl;

import com.training.aem.core.services.ProcessPages;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.ValueMap;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component(service = ProcessPages.class)
public class ProcessPagesImpl implements ProcessPages{

    @Reference
    private ResourceResolverFactory resolverFactory;

    private String rootPage = "us";





























    //    private Map<String, Object> getServiceUser() {
//        // Use a service user to obtain the ResourceResolver
//        Map<String, Object> serviceUserMap = new HashMap<>();
//        serviceUserMap.put(ResourceResolverFactory.SUBSERVICE, "myServiceUser");
//        return serviceUserMap;
//    }
//    @Override
//    public void ProcessChildPages(Resource parentPageResource) {
//
//            ValueMap parentPageProperties = parentPageResource.getValueMap();
//            String pathValue = parentPageProperties.get("path",String.class);
//            if(pathValue != null){
//
//            }
//            Iterator<Resource> childNodes = parentPageResource.listChildren();
//            while(childNodes.hasNext()){
//                Resource childNode = childNodes.next();
//                ProcessChildPages(childNode);
//            }
//
//
//
//
//
//    }
//
//    public void processParentPage(){
//        try{
//            ResourceResolver resolver = resolverFactory.getServiceResourceResolver(getServiceUser());
//            String parentPagePath = "/content/training-project/us";
//
//            Resource parentPageResource = resolver.getResource(parentPagePath);
//            if(parentPageResource != null){
//                ProcessChildPages(parentPageResource);
//            }
//
//
//
//        }catch(Exception e){
//            e.printStackTrace();
//
//        }
//
//    }
}
