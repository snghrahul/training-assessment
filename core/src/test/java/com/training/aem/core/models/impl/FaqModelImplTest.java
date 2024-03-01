package com.training.aem.core.models.impl;

import com.training.aem.core.bean.FaqEntity;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class FaqModelImplTest {

    AemContext aemContext = new AemContext();
    @Mock
    Resource faqs;

    @Mock
    private Resource childRes;
    @Mock
    ValueMap valueMap;
    @InjectMocks
    @Spy
    FaqModelImpl faqModel;

    @BeforeEach
    void setUp() {
        aemContext.addModelsForClasses(FaqModelImpl.class);

    }
    @Test
    void getFaqsList() {
        when(faqs.hasChildren()).thenReturn(true);
        when(faqs.getChildren()).thenReturn(Collections.singletonList(childRes));
        when(childRes.getValueMap()).thenReturn(valueMap);
        when(valueMap.get("question", String.class)).thenReturn("question");
        when(valueMap.get("answer", String.class)).thenReturn("answer");
        faqModel.getFaqsList();



    }



}