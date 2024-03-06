package com.training.aem.core.services.workflow;

import com.day.cq.workflow.WorkflowException;
import com.day.cq.workflow.WorkflowSession;
import com.day.cq.workflow.exec.WorkItem;
import com.day.cq.workflow.exec.WorkflowData;
import com.day.cq.workflow.metadata.MetaDataMap;
import com.training.aem.core.bean.ExcelRowDataEntity;
import com.training.aem.core.services.ExcelProcessingWorkflowService;
import com.training.aem.core.services.NodeCreationService;
import com.training.aem.core.services.impl.ExcelProcessingWorkflowServiceImpl;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class ExecuteWorkflowTest {
    AemContext aemContext = new AemContext();
    @InjectMocks
    ExecuteWorkflow workflow;
    @Mock
    NodeCreationService nodeCreationService;
    @Mock
    ExcelProcessingWorkflowServiceImpl excelProcessingWorkflowService;
    @Mock
    WorkItem workItem;
    @Mock
    WorkflowSession workflowSession;
    @Mock
    MetaDataMap metaDataMap;


    @BeforeEach
    void setUp() {
        aemContext.addModelsForClasses(ExecuteWorkflow.class);
    }

    @Test
    void execute() throws WorkflowException {
        ExcelRowDataEntity excelRowDataEntity = new ExcelRowDataEntity();
        List<ExcelRowDataEntity> rowDataEntityList = new ArrayList<>();
        rowDataEntityList.add(excelRowDataEntity);
        String payloadType = "JCR_PATH";
        String folderPath = "JCR_PATH";
        WorkflowData workflowData = mock(WorkflowData.class);

        when(workItem.getWorkflowData()).thenReturn(workflowData);
        when(workflowData.getPayloadType()).thenReturn(payloadType);
        when(excelProcessingWorkflowService.processExcelFile(folderPath)).thenReturn(rowDataEntityList);
        workflow.execute(workItem,workflowSession,metaDataMap);
        nodeCreationService.CreateNodeFromExcel(rowDataEntityList);



    }
}