package com.training.aem.core.services.impl;

import com.training.aem.core.services.AuthorGroupService;
import org.apache.jackrabbit.api.security.user.Authorizable;
import org.apache.jackrabbit.api.security.user.Group;
import org.apache.jackrabbit.api.security.user.UserManager;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.jcr.RepositoryException;
import java.util.*;

@Component(service = {AuthorGroupService.class})
public class AuthorGroupServiceImpl implements AuthorGroupService{

    @Reference
    ResourceResolverFactory resourceResolverFactory;
    @Override
    public List<String> isUserInGroup(String userName, String groupName) {
        List<String> memberslIst = new ArrayList<>();
        ResourceResolver resourceResolver = null;
        try{
            resourceResolver = getResourceResolver();
            if(resourceResolver != null){
                UserManager userManager = resourceResolver.adaptTo(UserManager.class);
                if(userManager != null){
                    Authorizable userGroup = userManager.getAuthorizable(groupName);
                    if(userGroup != null && userGroup.isGroup()){
                        Group group = (Group) userGroup;
                        Iterator<Authorizable> members = group.getMembers();
                        while(members.hasNext()){
                            Authorizable member = members.next();
                            if(member != null && !member.isGroup()){
                                memberslIst.add(member.getID());

                            }
                        }
                    }
                }
            }
        }catch (LoginException | RepositoryException e) {
            throw new RuntimeException(e);
        }
        return memberslIst;
    }

    private ResourceResolver getResourceResolver() throws LoginException {
        Map<String, Object> map  = new HashMap<>();
        map.put(ResourceResolverFactory.SUBSERVICE,"rahul");
        return resourceResolverFactory.getServiceResourceResolver(map);
    }


}
