package com.training.aem.core.servlets;

import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.AssetManager;
import com.day.cq.search.Predicate;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(service = {Servlet.class},
                      property = {"sling.servlet.paths=" + "/bin/search",
                              "sling.servlet.methods=" + HttpConstants.METHOD_GET

})
public class ProductSearchByQueryServlet extends SlingAllMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request,SlingHttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");
        if(StringUtils.isNotBlank(query) && query.length() > 3){
            try {
                List<String> suggestions = performSearch(request.getResourceResolver(),query);
            } catch (RepositoryException e) {
                throw new RuntimeException(e);
            }
        }

    }
    private List<String> performSearch(ResourceResolver resourceResolver,String query) throws RepositoryException {
        List<String> suggestions = new ArrayList<>();
        Map<String,Object> queryParams = new HashMap<>();
        queryParams.put("type","dam:Assest");
        queryParams.put("path","/content/dam/we-retail");
        queryParams.put("fulltext",query);
//        PredicateGroup predicateGroup = PredicateGroup.create(queryParams);
         Query query1 = resourceResolver.adaptTo(QueryBuilder.class).createQuery(PredicateGroup.create(queryParams),resourceResolver.adaptTo(Session.class));
        SearchResult result = query1.getResult();
        for(Hit hit : result.getHits()){
            String path = hit.getPath();
            AssetManager assetManager = resourceResolver.adaptTo(AssetManager.class);
            Asset asset = assetManager.getAssetForBinary(path);
            suggestions.add(asset.getName());

        }
        return suggestions;
    }
}
