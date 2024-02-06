package com.training.aem.core.services.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.aem.core.Constant.CommonConstant;
import com.training.aem.core.bean.ClientResponse;
import com.training.aem.core.models.ProductDetailsEntity;
import com.training.aem.core.services.ProductService;
import com.training.aem.core.utils.CommonUtils;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import javax.servlet.http.HttpServletResponse;


@Component(service = ProductService.class)
public final class ProductServiceImpl implements ProductService{

    @Override
    public ProductDetailsEntity getFakeApiData(String apiUrl) throws Exception {
        ProductDetailsEntity productEntity = new ProductDetailsEntity();
        try {
            ClientResponse clientResponse = CommonUtils
                    .getClientResponse(CommonConstant.GET,apiUrl,null,null);
            if (clientResponse != null && clientResponse.getStatusCode() == HttpServletResponse.SC_OK){
                JSONObject responseObj = new JSONObject(clientResponse.getData());
//                ObjectMapper objectMapper = new ObjectMapper();
//                productEntity = objectMapper.readValue(responseObj.toString(), ProductDetailsEntity.class);

                productEntity.setId((Integer) responseObj.get("id"));
                productEntity.setImage((String) responseObj.get("image"));
                productEntity.setPrice((Double) responseObj.get("price"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return productEntity;


    }

//    @Override
//    public ProductDetailsEntity getProductById(String productId, ProductDetailsEntity productDetails) {
//        return productDetails.getProductId().equals(productId) ? productDetails : null;
//
//    }

//    @Override
//    public String getImage(String productId) {
//        return null;
//    }
//
//    @Override
//    public String getPrice(String productId) {
//        return null;
//    }
}
