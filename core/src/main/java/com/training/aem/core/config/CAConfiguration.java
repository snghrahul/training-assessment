package com.training.aem.core.config;

import org.apache.sling.caconfig.annotation.Configuration;
import org.apache.sling.caconfig.annotation.Property;

@Configuration(label = "Context Aware Configuration",description = "product price needs to be shown\n" +
        "based on the CA configurations where the values are authored.")
public @interface CAConfiguration {
    @Property(label = "Country Site", description = "price")
    String price() default "";


}
