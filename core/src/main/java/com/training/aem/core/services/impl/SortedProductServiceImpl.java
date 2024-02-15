package com.training.aem.core.services.impl;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.training.aem.core.Constant.CommonConstant;
import com.training.aem.core.bean.ClientResponse;
import com.training.aem.core.models.ProductDetailsEntity;
import com.training.aem.core.services.SortedProductsService;
import com.training.aem.core.utils.CommonUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import javax.servlet.http.HttpServletResponse;
import javax.swing.table.TableCellEditor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Component(service = SortedProductsService.class)
public class SortedProductServiceImpl implements SortedProductsService{

    public List<ProductDetailsEntity> getAllProducts(String apiUrl){
        List<ProductDetailsEntity> productDetailsList = new ArrayList<>();
        try(CloseableHttpClient httpClient = HttpClients.createDefault()){
            HttpGet httpGet = new HttpGet(apiUrl);
            CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = closeableHttpResponse.getEntity();
//            ProductDetailsEntity productDetailsEntity = new Gson().fromJson(EntityUtils.toString(httpEntity), ProductDetailsEntity.class);
            JSONArray jsonArray = new JSONArray(EntityUtils.toString(httpEntity));
            // Iterate over the array and map each element to ProductDetailsEntity
//            List<ProductDetailsEntity> productDetailsList = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                // Map the JSON object to ProductDetailsEntity
                ProductDetailsEntity productDetailsEntity = new Gson().fromJson(jsonObject.toString(), ProductDetailsEntity.class);
                productDetailsList.add(productDetailsEntity);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


//        try {
//            ClientResponse clientResponse = CommonUtils
//                    .getClientResponse(CommonConstant.GET,apiUrl,null,null);
//            if (clientResponse != null && clientResponse.getStatusCode() == HttpServletResponse.SC_OK){
//                JSONArray jsonArray = new JSONArray();
//                jsonArray.put(clientResponse.getData());
//
//                if(jsonArray!=null){
//                    ObjectMapper objectMapper = new ObjectMapper();
//                    productEntity = objectMapper.readValue(String.valueOf(jsonArray), new TypeReference<List<ProductDetailsEntity>>() {
//                    });
//                }
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return productEntity;
        return productDetailsList;
    }
    @Override
    public List<ProductDetailsEntity> getSortedProducts(String sortType) {


        List<ProductDetailsEntity> products = getAllProducts("https://fakestoreapi.com/products/");
        Collections.sort(products, new ProductDetailsEntityComparator(sortType));

        return products;
    }

    public String getSortType(){
        return null;
    }
}
