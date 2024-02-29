package com.training.aem.core.services.impl;

import com.day.cq.dam.api.Asset;
import com.training.aem.core.bean.ExcelRowDataEntity;
import com.training.aem.core.services.ExcelProcessingWorkflowService;
import com.training.aem.core.services.NodeCreationService;
import opennlp.tools.util.InvalidFormatException;
import org.apache.commons.lang.ObjectUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.sling.api.resource.*;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.jcr.Node;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(service = {ExcelProcessingWorkflowService.class})
public class ExcelProcessingWorkflowServiceImpl implements ExcelProcessingWorkflowService{

    @Reference
    private NodeCreationService nodeCreationService;

    @Override
    public List<ExcelRowDataEntity> processExcelFile(String filePath) {
        List<ExcelRowDataEntity> rowDataEntityList = new ArrayList<>();
        try{
            InputStream stream = getFileInputStream(filePath);
            Workbook workbook = WorkbookFactory.create(stream);
            Sheet sheet = workbook.getSheetAt(0);

            for(Row row : sheet){
                ExcelRowDataEntity rowData = new ExcelRowDataEntity();
                rowData.setColumn1(row.getCell(0).getStringCellValue());
                rowData.setColumn2(row.getCell(1).getNumericCellValue());
                rowDataEntityList.add(rowData);

            }
        }catch (IOException e){
            e.getMessage();
        }
        return rowDataEntityList;
    }
    private InputStream getFileInputStream(String filePath){
        ResourceResolver resourceResolver = null;
        try{
            resourceResolver = nodeCreationService.getResourceResolver();
            Resource fileResource = resourceResolver.getResource(filePath);
            Asset asset = fileResource.adaptTo(Asset.class);
            return asset != null ? asset.getOriginal().getStream() : null;
        } catch (LoginException | ResourceNotFoundException | NullPointerException exception) {
            String errorMessage;
            if(exception instanceof LoginException){
                errorMessage = "Unable to obtain resource resolver" + ((LoginException) exception).getMessage();
            } else if (exception instanceof ResourceNotFoundException) {
                errorMessage = "Resource Not found at given path: " + ((ResourceNotFoundException) exception).getMessage();
            }else {
                errorMessage = "Null resource encuntered at path: " + ((NullPointerException) exception).getMessage();
            }
            throw new RuntimeException(errorMessage, exception);
        } finally {
            if (resourceResolver != null && resourceResolver.isLive()) {
                resourceResolver.close();
            }
        }
    }
}
