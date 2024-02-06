//package com.training.aem.core.models.impl;
//
//import com.training.aem.core.models.ProductSchedulerModel;
//import com.training.aem.core.services.ApiService;
//import org.apache.sling.api.SlingHttpServletRequest;
//import org.apache.sling.models.annotations.DefaultInjectionStrategy;
//import org.apache.sling.models.annotations.Model;
//import org.apache.sling.models.annotations.injectorspecific.OSGiService;
//
//@Model(adaptables = SlingHttpServletRequest.class,
//        adapters = {ProductSchedulerModel.class},
//        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
//public class ProductSchedulerModelImpl implements ProductSchedulerModel {
//
//    @OSGiService
//    ApiService apiService
//
//    protected void init() throws Exception {
//        apiService.fetchDataAndCreatePages();
//    }
//
//}
