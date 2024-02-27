package com.training.aem.core.schedulers;

import com.training.aem.core.services.ApiService;
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = Runnable.class,immediate = true)
@Designate(ocd = SchedulerConfig.class)
public class FetchProductScheduler implements Runnable{

    @Reference
    private ApiService apiService;
    @Reference
    private Scheduler scheduler;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Activate
    protected void active(SchedulerConfig config)  {
//        getAllData(config);
        logger.info("active");
    }
    private void getAllData(SchedulerConfig config){
        ScheduleOptions scheduleOptions = scheduler.EXPR(config.scheduler_expression());
        scheduleOptions.canRunConcurrently(false);
        scheduler.schedule(this,scheduleOptions);
        try {
            apiService.fetchDataAndCreatePages();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void run() {
        logger.info("Custom Scheduler added");
        try {
            apiService.fetchDataAndCreatePages();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
