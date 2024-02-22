package com.training.aem.core.servlets;

import com.google.gson.Gson;
import com.training.aem.core.bean.ProductDetailsEntity;
import com.training.aem.core.services.SortedProductsService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
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
            String sortType = request.getParameter("sortType");
            List<ProductDetailsEntity> filteredProducts = filterProductsBasedOnSortType(sortType);
            String jsonResponse = new Gson().toJson(filteredProducts);
            response.setContentType("application/json");
            response.getWriter().write(jsonResponse);
    }

    private List<ProductDetailsEntity> filterProductsBasedOnSortType(String sortType){
        List<ProductDetailsEntity> fetchedSortedAllProducts = sortedProductsService.getSortedProducts(sortType);
        return fetchedSortedAllProducts;
    }
}
