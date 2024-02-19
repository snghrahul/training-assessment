package com.training.aem.core.models;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.training.aem.core.services.ProcessPages;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.Self;

@Model(adaptables = {SlingHttpServletRequest.class},defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class DynamicFlowModel{

    @OSGiService
    PageManager pageManager;

    @Self
    SlingHttpServletRequest request;
    private Page currentPage;

    public String getScript(){
        Resource currentResource = request.getResource();
        pageManager = currentResource.getResourceResolver().adaptTo(PageManager.class);
        currentPage = pageManager.getContainingPage(currentResource);
        Page parentPage = getParentScriptPage(currentPage);
        if(parentPage != null){
            ValueMap parentPageproperties = parentPage.getProperties();
            if(parentPageproperties.containsKey("path")){
                return parentPageproperties.get("path",String.class);
            }
        }
    return "";
    }
    public Page getParentScriptPage(Page currentPage){
        if(currentPage != null){
            ValueMap properties = currentPage.getProperties();
            if(properties.containsKey("path")){
                return currentPage;
            }else{
                Page parentPage = currentPage.getParent();
                if(parentPage == null || parentPage.equals(currentPage)){
                    return currentPage;
                }
                return  getParentScriptPage(parentPage);
            }
        }
        return null;
    }
}
