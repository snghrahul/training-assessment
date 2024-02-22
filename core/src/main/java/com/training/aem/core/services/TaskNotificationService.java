package com.training.aem.core.services;

import com.adobe.granite.taskmanagement.TaskManagerException;

public interface TaskNotificationService {
    public void setTaskNotification(String pagePath) throws TaskManagerException;
}
