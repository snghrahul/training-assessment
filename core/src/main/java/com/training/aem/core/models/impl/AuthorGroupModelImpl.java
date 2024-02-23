package com.training.aem.core.models.impl;


import com.training.aem.core.models.AuthorGroupModel;
import com.training.aem.core.services.AuthorGroupService;
import org.apache.jackrabbit.api.security.user.Authorizable;
import org.apache.jackrabbit.api.security.user.Group;
import org.apache.jackrabbit.api.security.user.UserManager;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.jcr.RepositoryException;
import java.util.*;

@Model(adaptables = {SlingHttpServletRequest.class},
                     adapters = AuthorGroupModel.class,
                     defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class AuthorGroupModelImpl implements AuthorGroupModel{

//    @OSGiService
//    AuthorGroupService authorGroupService;

    @OSGiService
    ResourceResolverFactory resourceResolverFactory;

    @SlingObject
    ResourceResolver resourceResolver;

    @ValueMapValue
    String group;
    @Override
    public String getAllUsers() {

        List<String> memberslIst = new ArrayList<>();
        try{
            if(resourceResolver != null){
                UserManager userManager = resourceResolver.adaptTo(UserManager.class);
                if(userManager != null){
                    Authorizable userGroup = userManager.getAuthorizable(group);
                    if(userGroup != null && userGroup.isGroup()){
                        Group group = (Group) userGroup;
                        Iterator<Authorizable> members = group.getMembers();
                        while(members.hasNext()){
                            Authorizable member = members.next();
                            if(member != null && !member.isGroup()){
                                memberslIst.add(member.getID());
                                if(member.getID().equals("rahul123")){
                                    return member.getID();
                                }
                            }
                        }
                    }
                }
            }
        }catch (RepositoryException e) {
            throw new RuntimeException(e);
        }

        return null;


    }

    private ResourceResolver getResourceResolver() throws LoginException {
        Map<String, Object> map  = new HashMap<>();
        map.put(ResourceResolverFactory.SUBSERVICE,"rahul123");
        return resourceResolverFactory.getServiceResourceResolver(map);
    }
}
