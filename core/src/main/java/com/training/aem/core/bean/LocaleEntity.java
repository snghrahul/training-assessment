package com.training.aem.core.bean;

import java.util.List;

public class LocaleEntity {
    private String projectType;
    private List<String> childNodes;
    public LocaleEntity(String projectType, List<String> childNodes) {
        this.projectType = projectType;
        this.childNodes = childNodes;
    }
}
