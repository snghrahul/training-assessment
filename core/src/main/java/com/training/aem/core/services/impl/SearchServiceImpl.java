//package com.training.aem.core.services.impl;
//
//import com.day.cq.search.PredicateGroup;
//import com.day.cq.search.Query;
//import com.day.cq.search.QueryBuilder;
//import com.day.cq.search.result.Hit;
//import com.day.cq.search.result.SearchResult;
//import com.day.cq.wcm.api.Page;
//import com.training.aem.core.services.SearchService;
//import org.apache.sling.api.resource.LoginException;
//import org.apache.sling.api.resource.ResourceResolver;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.osgi.service.component.annotations.Component;
//import org.osgi.service.component.annotations.Reference;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.jcr.RepositoryException;
//import javax.jcr.Session;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Component(service = {SearchService.class})
//public class SearchServiceImpl implements SearchService{
//    @Reference
//    NodeCreationServiceImpl nodeCreationService;
//    @Reference
//    QueryBuilder queryBuilder;
//    private static final Logger logger = LoggerFactory.getLogger(SearchServiceImpl.class);
//    @Override
//    public JSONObject searchResult(String searchText) throws LoginException, RepositoryException, JSONException {
//        JSONObject searchResult = new JSONObject();
//        ResourceResolver resourceResolver = nodeCreationService.getResourceResolver();
//        final Session session = resourceResolver.adaptTo(Session.class);
//        Query query = queryBuilder.createQuery(PredicateGroup.create(createTextSearchQuery(searchText)),session);
//        SearchResult result = query.getResult();
//        List<Hit> hits = result.getHits();
//        JSONArray resultArray = new JSONArray();
//        for(Hit hit : hits){
//            Page page = hit.getResource().adaptTo(Page.class);
//            JSONObject resultObject = new JSONObject();
//            resultObject.put("title",page.getTitle());
//            resultObject.put("path",page.getPath());
//            resultArray.put(resultObject);
//            logger.info("\n Page {} ",page.getPath());
//        }
//        searchResult.put("results",resultArray);
//
//
//
//        return searchResult;
//    }
//
//    private Map<String,String> createTextSearchQuery(String searchText){
//        Map<String,String> map = new HashMap<>();
//        map.put("path","/content/we-retail");
//        map.put("type","cq:Page");
//        map.put("fulltext",searchText);
//        map.put("p.limit","-1");
//        return map;
//
//    }
//}
