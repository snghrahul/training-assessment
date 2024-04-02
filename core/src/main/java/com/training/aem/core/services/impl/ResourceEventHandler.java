package com.training.aem.core.services.impl;

import org.apache.sling.api.resource.observation.ResourceChange;
import org.apache.sling.api.resource.observation.ResourceChangeListener;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Component(service = ResourceChangeListener.class,
                property = {
                ResourceChangeListener.PATHS+"=/content/training-project/us/en/home",
                ResourceChangeListener.CHANGES+"=ADDED",
                         ResourceChangeListener.CHANGES+"=REMOVED",
                        ResourceChangeListener.CHANGES+"=CHANGED",

        }
)
public class ResourceEventHandler implements ResourceChangeListener {
    private static final Logger logger = LoggerFactory.getLogger(ResourceChangeListener.class);
    @Override
    public void onChange(List<ResourceChange> list) {
        if(!list.isEmpty()){

            for(ResourceChange rc : list){
                logger.info("\n Event : {} , Resource : {} ",rc.getType(),rc.getPath());
            }
        }else {
            logger.error("unable to create page");
        }


    }
}
