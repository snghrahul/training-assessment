package com.training.aem.core.models.impl;

import com.training.aem.core.bean.FaqEntity;
import com.training.aem.core.models.FaqModel;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.util.ArrayList;
import java.util.List;

@Model(adaptables = {SlingHttpServletRequest.class},
        adapters = {FaqModel.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FaqModelImpl implements FaqModel{
    @ChildResource
    private Resource faqs;
    @ValueMapValue
    private String question;

    @ValueMapValue
    private String answer;

    List<FaqEntity> faqsList = new ArrayList<>();

    @Override
    public List<FaqEntity> getFaqsList() {
        if(faqs != null && faqs.hasChildren()){
            for(Resource resource : faqs.getChildren()){
                ValueMap map = resource.getValueMap();
                FaqEntity faq = new FaqEntity();
                faq.setQuestion(map.get("question",String.class));
                faq.setAnswer(map.get("answer",String.class));
                faqsList.add(faq);
            }
        }
        return faqsList;
    }
}

