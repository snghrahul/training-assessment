package com.training.aem.core.services.impl;

import com.training.aem.core.bean.ExcelRowDataEntity;
import com.training.aem.core.services.NodeCreationService;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.jcr.Node;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(service = {NodeCreationService.class})
public class NodeCreationServiceImpl implements NodeCreationService{
    @Reference
    private ResourceResolverFactory resolverFactory;
    @Override
    public void CreateNodeFromExcel(List<ExcelRowDataEntity> rowDataEntitList) {
        ResourceResolver resourceResolver = null;
        try{
            resourceResolver = getResourceResolver();
            Resource parentResouce = resourceResolver.getResource("/content/training-project");
            Node parentNode = parentResouce.adaptTo(Node.class);
            if(parentNode != null){
                for(ExcelRowDataEntity rowData : rowDataEntitList){
                        String nodeName = generateUniqueNodeName();
                        Node node = parentNode.addNode(nodeName,"nt:unstructured");
                        node.setProperty("columnName1", rowData.getColumn1());
                        node.setProperty("columnName2",rowData.getColumn2());
                }
                resourceResolver.commit();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(resourceResolver != null && resourceResolver.isLive()){
                resourceResolver.close();
            }
        }
        }
    @Override
    public ResourceResolver getResourceResolver() throws LoginException {
        Map<String, Object> map  = new HashMap<>();
        map.put(ResourceResolverFactory.SUBSERVICE,"rahul");
        return resolverFactory.getServiceResourceResolver(map);
    }

    private String generateUniqueNodeName() {
        return "node_" + System.currentTimeMillis();
    }
}
