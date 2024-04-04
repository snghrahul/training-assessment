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
    private SchedulerConfig config;

    @Activate
    protected void active(SchedulerConfig config)  {
        logger.info("active");
        getAllData(config);
    }
    private void getAllData(SchedulerConfig config){
        ScheduleOptions scheduleOptions = scheduler.EXPR(config.sch_expression());
        scheduleOptions.canRunConcurrently(false);
        scheduler.schedule(this,scheduleOptions);
        try {
            apiService.fetchDataAndCreatePages();
            logger.info("api called");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void run() {
        logger.info("scheduler is running");
        try {
            apiService.fetchDataAndCreatePages();
            logger.info("api called");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        getAllData(config);
    }
}
