package com.training.aem.core.services.impl;

import com.day.cq.replication.AgentManager;
import com.day.cq.replication.ReplicationActionType;
import com.day.cq.replication.ReplicationException;
import com.day.cq.replication.Replicator;
import com.training.aem.core.bean.ProductDetailsEntity;
import com.training.aem.core.services.ApiService;
import com.training.aem.core.services.PageCreationService;
import com.training.aem.core.services.ProductService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Session;
import java.util.HashMap;
import java.util.Map;

@Component(service = ApiService.class)
public class ApiServiceImpl implements ApiService {

    @Reference
    PageCreationService pageCreationService;
    @Reference
    ProductService productService;
    @Override
    public void fetchDataAndCreatePages() throws Exception {
        ProductDetailsEntity productDetails = productService.getFakeApiData("https://fakestoreapi.com/products/1");
        pageCreationService.createPages();
    }
}
