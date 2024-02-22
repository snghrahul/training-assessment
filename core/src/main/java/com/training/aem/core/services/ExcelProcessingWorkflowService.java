package com.training.aem.core.services;

import com.training.aem.core.bean.ExcelRowDataEntity;

import java.util.List;

public interface ExcelProcessingWorkflowService {
    List<ExcelRowDataEntity> processExcelFile(String filePath);
}
