package com.training.aem.core.models.impl;

import com.adobe.granite.ui.components.rendercondition.RenderCondition;
import com.adobe.granite.ui.components.rendercondition.SimpleRenderCondition;
import com.training.aem.core.models.AuthorGroupModel;
import org.apache.jackrabbit.api.security.user.Authorizable;
import org.apache.jackrabbit.api.security.user.Group;
import org.apache.jackrabbit.api.security.user.UserManager;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.RepositoryException;
import java.util.*;

@Model(adaptables = {SlingHttpServletRequest.class},
                     adapters = AuthorGroupModel.class,
                     defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class AuthorGroupModelImpl implements AuthorGroupModel{

    @Self
    private SlingHttpServletRequest request;
    @ValueMapValue
    private String group;

    @SlingObject
    ResourceResolver resourceResolver;

    private static final Logger logger = LoggerFactory.getLogger(AuthorGroupModelImpl.class);
    @Override
    public void getAllUsers()  {
        getAuthorUser();

    }

    private void getAuthorUser(){
        List<String> allowedGroups = Arrays.asList(group.split(","));
        UserManager userManager = resourceResolver.adaptTo(UserManager.class);
        if(userManager == null){
            return;
        }
        boolean belongsToGroup = false;
        try{
            Authorizable currentUser = userManager.getAuthorizable(resourceResolver.getUserID());
            Iterator<Group> groupsIt = currentUser.memberOf();
            while(groupsIt.hasNext()){
                Group group1 = groupsIt.next();
                String groupId = group1.getID();
                if(allowedGroups.stream().anyMatch(g->g.equals(groupId))){
                    belongsToGroup = true;
                    break;
                }
            }
        }catch (RepositoryException e){
            logger.error("User is not a member of content-authors {} " + e.getMessage());
        }
        request.setAttribute(RenderCondition.class.getName(),new SimpleRenderCondition(belongsToGroup));

    }
}
