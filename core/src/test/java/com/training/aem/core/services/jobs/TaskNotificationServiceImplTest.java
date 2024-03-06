package com.training.aem.core.services.jobs;

import com.adobe.granite.taskmanagement.Task;
import com.adobe.granite.taskmanagement.TaskManager;
import com.adobe.granite.taskmanagement.TaskManagerException;
import com.adobe.granite.taskmanagement.TaskManagerFactory;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
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

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class TaskNotificationServiceImplTest {
    AemContext aemContext = new AemContext();
    @Mock
    ResourceResolverFactory resourceResolverFactory;
    @Mock
    ResourceResolver resourceResolver;
    @InjectMocks
    TaskNotificationServiceImpl taskNotificationService;
    @Mock
    TaskManager taskManager;
    @Mock
    TaskManagerFactory taskManagerFactory;
    @Mock
    Resource resource;
    @Mock
    Task newTask;


    @BeforeEach
    void setUp() {
        aemContext.addModelsForClasses(TaskNotificationServiceImpl.class);
    }

    @Test
    void setTaskNotification() throws LoginException, TaskManagerException {
        String pagePath = "/content/training-project";
        Map<String, Object> map  = new HashMap<>();
        map.put(ResourceResolverFactory.SUBSERVICE,"rahul");
        when(resourceResolverFactory.getServiceResourceResolver(map)).thenReturn(resourceResolver);
        aemContext.registerService(ResourceResolverFactory.class,resourceResolverFactory);
        when(resourceResolver.adaptTo(TaskManager.class)).thenReturn(taskManager);
        when(taskManager.getTaskManagerFactory()).thenReturn(taskManagerFactory);
        when(resourceResolver.getResource(pagePath)).thenReturn(resource);
        when(taskManagerFactory.newTask("Notification")).thenReturn(newTask);
        newTask.setName("page is created");
        newTask.setContentPath(pagePath);
        newTask.setCurrentAssignee("admin");
        taskManager.createTask(newTask);
        taskNotificationService.setTaskNotification(pagePath);

    }
}