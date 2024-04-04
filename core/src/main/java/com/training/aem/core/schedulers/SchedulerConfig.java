package com.training.aem.core.schedulers;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Scheduler Configuration", description = "Product Scheduler Configuration")
public @interface SchedulerConfig {

    @AttributeDefinition(name = "Scheduler name",
            description = "Name of the Scheduler",
            type = AttributeType.STRING)
    public String schedulerName() default "Custom Sling Scheduler Configuration";

    @AttributeDefinition(name = "Cron name",
            description = "Cron Expression used by scheduler",
            type = AttributeType.STRING)
    public String sch_expression() default "";

}

