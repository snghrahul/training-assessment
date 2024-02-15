package com.training.aem.core.servlets;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.training.aem.core.models.ProductDetailsEntity;
import com.training.aem.core.services.SortedProductsService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component(service = {Servlet.class},
property = {
        "sling.servlet.paths="+"/bin/products",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET
})
public class ProductSearchServlet extends SlingAllMethodsServlet {

    @Reference
    private SortedProductsService sortedProductsService;

    @Override
    protected void doGet(SlingHttpServletRequest request,SlingHttpServletResponse response) throws ServletException, IOException {
//        String query = request.getParameter("query");
//        if(query != null && query.length() > 3){
            String sortType = request.getParameter("sortType");
            List<ProductDetailsEntity> suggestions = searchProducts(sortType);


            String jsonResponse = new Gson().toJson(suggestions);
//        JsonArray jsonObject = new JsonArray();
            response.setContentType("application/json");
        response.getWriter().write(jsonResponse);


//        }
    }

    private List<ProductDetailsEntity> searchProducts(String sortType){

        List<ProductDetailsEntity> allProducts = sortedProductsService.getSortedProducts(sortType);
        return allProducts;


    }
}
