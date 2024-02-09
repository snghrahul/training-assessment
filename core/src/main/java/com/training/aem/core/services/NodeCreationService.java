package com.training.aem.core.services;

import com.training.aem.core.bean.ExcelRowDataEntity;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;

import java.util.List;

public interface NodeCreationService {
    void CreateNodeFromExcel(List<ExcelRowDataEntity> rowDataEntitList);
    ResourceResolver getResourceResolver() throws LoginException;
}
