package com.training.aem.core.schedulers;

import com.training.aem.core.services.ApiService;
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
    ApiService apiService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Activate
    protected void active(SchedulerConfig config)  {
        logger.info("active");

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
