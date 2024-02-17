package com.training.aem.core.services.impl;


import com.training.aem.core.Constant.CommonConstant;
import com.training.aem.core.bean.ClientResponse;
import com.training.aem.core.bean.ProductDetailsEntity;
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
                productEntity.setId((Integer) responseObj.get("id"));
                productEntity.setImage((String) responseObj.get("image"));
                productEntity.setCategory((String) responseObj.get("category"));
                if(responseObj.get("price") instanceof Integer ){
                    productEntity.setPrice((Integer) responseObj.get("price"));
                }else{
                    productEntity.setPrice((Double) responseObj.get("price"));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return productEntity;
    }
}
