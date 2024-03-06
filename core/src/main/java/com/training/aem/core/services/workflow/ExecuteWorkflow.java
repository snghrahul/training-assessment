package com.training.aem.core.services.workflow;

import com.day.cq.workflow.WorkflowException;
import com.day.cq.workflow.WorkflowSession;
import com.day.cq.workflow.exec.WorkItem;
import com.day.cq.workflow.exec.WorkflowData;
import com.day.cq.workflow.exec.WorkflowProcess;
import com.day.cq.workflow.metadata.MetaDataMap;
import com.training.aem.core.bean.ExcelRowDataEntity;
import com.training.aem.core.services.ExcelProcessingWorkflowService;
import com.training.aem.core.services.NodeCreationService;
import org.apache.commons.lang.StringUtils;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Session;
import java.util.List;

@Component(service = {WorkflowProcess.class},immediate = true,property = {
        "process.label=Excel Sheet Custom Workflow Manager"
})
public class ExecuteWorkflow implements WorkflowProcess{

    @Reference
    ExcelProcessingWorkflowService excelProcessingWorkflowService;

    @Reference
    NodeCreationService nodeCreationService;
    protected final Logger logger = LoggerFactory.getLogger(ExecuteWorkflow.class);
    @Override
    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap) throws WorkflowException {

        String payloadType = workItem.getWorkflowData().getPayloadType();
        String path = "";
        if(StringUtils.equals(payloadType,"JCR_PATH")){
            logger.error("Payload type {}", payloadType);
            path = payloadType.toString();
        }

        String args = metaDataMap.get("PROCESS_ARGS",String.class);
        logger.error("Process args {}", args);

        String substringToRemove = "/jcr:content/renditions/original";
        String folderPath = path.replace(substringToRemove,"");
        List<ExcelRowDataEntity> rowDataEntityList = excelProcessingWorkflowService.processExcelFile(folderPath);
        nodeCreationService.CreateNodeFromExcel(rowDataEntityList);
    }
}
