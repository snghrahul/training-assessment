package com.training.aem.core.models.impl;

import com.training.aem.core.bean.AlertContentFragmentEntity;
import com.training.aem.core.models.ContentFragmentModel;
import com.training.aem.core.services.ContentFragmentService;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.List;


@Model(adaptables = {SlingHttpServletRequest.class},
        adapters = {ContentFragmentModel.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ContentFragmentModelImpl implements ContentFragmentModel{

    @SlingObject
    SlingHttpServletRequest request;
    @OSGiService
    ContentFragmentService contentFragmentService;
    List<AlertContentFragmentEntity> contentFragmentList = new ArrayList<>();

    @Override
    public List<AlertContentFragmentEntity> getAllContentFragments() throws LoginException {
        contentFragmentList = contentFragmentService.getContentFragmentData();
        String cookieName = "myCookie";
        Cookie myCookie = getCookie(cookieName);
        String myCookieValue = "";
        if(myCookie != null && !myCookie.getValue().isBlank()){
            myCookieValue = myCookie.getValue();
        }

        return contentFragmentList;
    }
    private Cookie getCookie(String cookieName){
        if (StringUtils.isBlank(cookieName)) {
            return null;
        } else {
            Cookie[] cookies = request.getCookies();
            if (cookies == null) {
                return null;
            } else {
                if (cookies.length > 0) {
                   for (Cookie cookie : cookies){
                       if(cookie.getName().equals(cookieName)){
                           return cookie;
                       }
                   }
                }
                return null;
            }
        }
    }
}
