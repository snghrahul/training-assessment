package com.training.aem.core.services.impl;

import com.day.cq.dam.api.Asset;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.commons.ReferenceSearch;
import com.training.aem.core.services.NodeCreationService;
import com.training.aem.core.services.OrphanAssets;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.util.*;

@Component(service = {OrphanAssets.class})
public class OrphanAssetsImpl implements OrphanAssets{
    @Reference
    NodeCreationService nodeCreationService;
    @Reference
    QueryBuilder queryBuilder;
    private static final Logger logger = LoggerFactory.getLogger(OrphanAssetsImpl.class);
    @Override
    public Map<String,String> getAllOrphanAssets(String path) throws LoginException, RepositoryException {
        Map<String,String> orphanAssetsList = new HashMap<>();
        Map<String,String> queryMap = new HashMap<>();
        ResourceResolver resourceResolver = nodeCreationService.getResourceResolver();
        ReferenceSearch referenceSearch = new ReferenceSearch();
        queryMap.put("type","dam:Asset");
        queryMap.put("path",path);
        queryMap.put("path.exact","true");

        Query query = queryBuilder.createQuery(PredicateGroup.create(queryMap),resourceResolver.adaptTo(Session.class));
        SearchResult result = query.getResult();
        List<Hit> hits = result.getHits();
        for(Hit hit : hits){
            String assetPath = hit.getPath();
            Resource resource = hit.getResource();
            Asset asset1 = resource.adaptTo(Asset.class);
            String assetName = asset1.getName();
            Collection<ReferenceSearch.Info> references = referenceSearch.search(resourceResolver, assetPath).values();
            if(references.isEmpty()){
                orphanAssetsList.put(assetPath,assetName);
            }
            logger.info(references.toString());
        }
        return orphanAssetsList;





    }
}
