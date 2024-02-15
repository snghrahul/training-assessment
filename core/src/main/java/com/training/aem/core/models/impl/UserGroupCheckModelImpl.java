package com.training.aem.core.models.impl;

import com.training.aem.core.bean.UserGroupCheckEntity;
import org.apache.jackrabbit.api.security.user.Authorizable;
import org.apache.jackrabbit.api.security.user.UserManager;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import javax.annotation.PostConstruct;
import javax.jcr.RepositoryException;

@Model(adaptables = {SlingHttpServletRequest.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class UserGroupCheckModelImpl {
    @SlingObject
    private ResourceResolver resourceResolver;

    @OSGiService
    private ResourceResolverFactory resolverFactory;

    @PostConstruct
    protected void init(){
        UserGroupCheckEntity userGroupCheckEntity = new UserGroupCheckEntity();

    }
}
