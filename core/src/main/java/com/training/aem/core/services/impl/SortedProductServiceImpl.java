package com.training.aem.core.services.impl;

import com.google.gson.Gson;
import com.training.aem.core.bean.ProductDetailsEntity;
import com.training.aem.core.services.SortedProductsService;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component(service = SortedProductsService.class)
public class SortedProductServiceImpl implements SortedProductsService{
    private static final Logger logger = LoggerFactory.getLogger(SortedProductServiceImpl.class);
    public List<ProductDetailsEntity> getAllProducts(String apiUrl){
        List<ProductDetailsEntity> productDetailsList = new ArrayList<>();
        try(CloseableHttpClient httpClient = HttpClients.createDefault()){
            HttpGet httpGet = new HttpGet(apiUrl);
            CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = closeableHttpResponse.getEntity();
            JSONArray jsonArray = new JSONArray(EntityUtils.toString(httpEntity));
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                ProductDetailsEntity productDetailsEntity = new Gson().fromJson(jsonObject.toString(), ProductDetailsEntity.class);
                productDetailsList.add(productDetailsEntity);
            }
        } catch (IOException | JSONException exception) {
            logger.error("Error occurred while fetching product details: " + exception.getMessage());
        }
        return productDetailsList;
    }
    @Override
    public List<ProductDetailsEntity> getSortedProducts(String sortType) {
        List<ProductDetailsEntity> products = getAllProducts("https://fakestoreapi.com/products/");
        Collections.sort(products, new ProductDetailsEntityComparator(sortType));
        return products;
    }
}
