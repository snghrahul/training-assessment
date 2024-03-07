package com.training.aem.core.servlets;

import com.training.aem.core.bean.ExcelRowDataEntity;
import com.training.aem.core.services.ExcelProcessingWorkflowService;
import com.training.aem.core.services.NodeCreationService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

@Component(service = {Servlet.class},
        property = {
                "sling.servlet.paths=" + "/bin/readData",
                "sling.servlet.methods=" + HttpConstants.METHOD_GET
        })

public class TempServlet extends SlingAllMethodsServlet {

    @Reference
    ExcelProcessingWorkflowService excelProcessingWorkflowService;

    @Reference
    NodeCreationService nodeCreationService;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write("heyy");
        String folderPath = "/content/dam/training-project/temp.xlsx";
        List<ExcelRowDataEntity> rowDataEntityList = excelProcessingWorkflowService.processExcelFile(folderPath);
        nodeCreationService.CreateNodeFromExcel(rowDataEntityList);
    }

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");



    }
}
