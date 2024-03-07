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
import org.apache.sling.api.resource.*;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

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
    @Reference
    ResourceResolverFactory resourceResolverFactory;
    @Override
    protected void doGet(SlingHttpServletRequest request,SlingHttpServletResponse response) throws ServletException, IOException {
        List<String> suggestions;
        String query = request.getParameter("query");
        if(StringUtils.isNotBlank(query) && query.length() > 3){
            try {
                    suggestions = performSearch(request.getResourceResolver(),query);

            } catch (LoginException | RepositoryException e) {
                throw new RuntimeException(e);
            }
            response.getWriter().write(suggestions.toString());
        }
    }
    private List<String> performSearch(ResourceResolver resourceResolver,String query) throws RepositoryException, LoginException {
        List<String> suggestions = new ArrayList<>();
        QueryBuilder queryBuilder = resourceResolver.adaptTo(QueryBuilder.class);
        Map<String,Object> queryParams = new HashMap<>();
        queryParams.put("type","dam:Assets");
        queryParams.put("path","/var/commerce/products/we-retail");
        queryParams.put("fulltext",query + "*");
        queryParams.put("p.limit","-1");


        Query query1 = queryBuilder.createQuery(PredicateGroup.create(queryParams),resourceResolver.adaptTo(Session.class));
        SearchResult result = query1.getResult();
        Resource resource;
        for(Hit hit : result.getHits()){
            String path = hit.getPath();
            resource = resourceResolver.getResource(path);
            ValueMap vm = resource.getValueMap();
            if(vm.containsKey("jcr:title")){
                suggestions.add(vm.get("jcr:title",String.class));
            }
        }
        return suggestions;
    }
}
