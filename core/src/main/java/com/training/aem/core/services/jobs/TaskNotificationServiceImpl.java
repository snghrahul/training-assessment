package com.training.aem.core.services.jobs;

import com.adobe.granite.taskmanagement.Task;
import com.adobe.granite.taskmanagement.TaskManager;
import com.adobe.granite.taskmanagement.TaskManagerException;
import com.adobe.granite.taskmanagement.TaskManagerFactory;
import com.google.common.base.Strings;
import com.training.aem.core.services.TaskNotificationService;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@Component(service = {TaskNotificationService.class},immediate = true)
public class TaskNotificationServiceImpl implements TaskNotificationService{
    @Reference
    ResourceResolverFactory resourceResolverFactory;

    private final Logger logger = LoggerFactory.getLogger(TaskNotificationServiceImpl.class);
    public static final String NOTIFICATION_TASK_TYPE = "Notification";
    private ResourceResolver getResourceResolver() {
        Map<String, Object> map = new HashMap<>();
        map.put(resourceResolverFactory.SUBSERVICE,"rahul");
        ResourceResolver serviceResourceResolver = null;
        try {
            serviceResourceResolver = resourceResolverFactory.getServiceResourceResolver(map);
        } catch (LoginException e) {
            logger.error("Could not get service user [ {} ]”, “demoSystemUser", e.getMessage());
        }
        return serviceResourceResolver;
    }
    @Override
    public void setTaskNotification(String pagePath) throws TaskManagerException {
        ResourceResolver resourceResolver = getResourceResolver();
        TaskManager taskManager = resourceResolver.adaptTo(TaskManager.class);
        TaskManagerFactory taskManagerFactory = taskManager.getTaskManagerFactory();
        if(!Strings.isNullOrEmpty(pagePath)){
            Resource resource = resourceResolver.getResource(pagePath);
        }
        Task newTask = taskManagerFactory.newTask(NOTIFICATION_TASK_TYPE);
        newTask.setName("page is created");
        newTask.setContentPath(pagePath);
        newTask.setCurrentAssignee("admin");
        taskManager.createTask(newTask);


    }
}
