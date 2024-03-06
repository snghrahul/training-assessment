package com.training.aem.core.services.impl;

import com.day.cq.replication.ReplicationActionType;
import com.day.cq.replication.ReplicationException;
import com.day.cq.replication.Replicator;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.WCMException;
import com.training.aem.core.services.TaskNotificationService;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.jcr.Session;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class PageCreationServiceImplTest {
    AemContext aemContext = new AemContext();
    @Mock
    SlingHttpServletRequest request;
    @Mock
    ResourceResolverFactory resourceResolverFactory;
    @Mock
    TaskNotificationService taskNotificationService;
    @InjectMocks
    PageCreationServiceImpl pageCreationService;
    @Mock
    ResourceResolver resourceResolver;
    @Mock
    Resource resource;
    @Mock
    PageManager pageManager;
    @Mock
    Page newPage;
    @Mock
    Session session;

    @Mock
    private Replicator replicator;

    @BeforeEach
    void setUp() {
        aemContext.addModelsForClasses(PageCreationServiceImpl.class);
    }

    @Test
    void createPages() throws LoginException, WCMException, ReplicationException {
        String parentPath = "/content/training-project/us";
        String templatePath = "conf/training-project/settings/wcm/templates/page-content";
        Map<String, Object> map  = new HashMap<>();
        map.put(ResourceResolverFactory.SUBSERVICE,"rahul");
        when(resourceResolverFactory.getServiceResourceResolver(map)).thenReturn(resourceResolver);
        when(resourceResolver.getResource(parentPath)).thenReturn(resource);
        when(resourceResolver.adaptTo(PageManager.class)).thenReturn(pageManager);
        when(pageManager.create(
                "/content/training-project/us",
                "page1",
                "/conf/training-project/settings/wcm/templates/page-content",
                "page created",
                true
        )).thenReturn(newPage);
        when(newPage.getPath()).thenReturn("abc/newPagePat");
        when(resourceResolver.adaptTo(Session.class)).thenReturn(session);
        doNothing().when(replicator).replicate(session, ReplicationActionType.ACTIVATE,"abc/newPagePat");
        pageCreationService.createPages();
//        pageCreationService.createPage(parentPath,"page1",templatePath,null);
    }

    @Test
    void getResourceResolver() {
    }
}