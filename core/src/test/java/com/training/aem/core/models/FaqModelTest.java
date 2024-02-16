//package com.training.aem.core.models;
//
//import io.wcm.testing.mock.aem.junit5.AemContextExtension;
//import org.apache.sling.api.resource.Resource;
//import org.apache.sling.api.resource.ValueMap;
//import org.apache.sling.testing.mock.sling.context.SlingContextImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//@ExtendWith({AemContextExtension.class, MockitoExtension.class})
//class FaqModelTest {
//    @Mock
//    private Resource faqs;
//
//    @Mock
//    private Resource childResource;
//
//    @Mock
//    private Resource childResource2;
//
//    @Mock
//    private ValueMap valueMap1;
//
//    @Mock
//    private ValueMap valueMap2;
//
//    @BeforeEach
//    void setUp() {
////        Resource faqResource = Mockito.mock(Resource.class);
////        when(faqResource.hasChildren()).thenReturn(true);
////
////        Resource childResource1 = Mockito.mock(Resource.class);
////        when(childResource1.getValueMap()).thenReturn(new Map<>());
////        when(childResource1.getValueMap().get("question", String.class)).thenReturn("Question 1");
////        when(childResource1.getValueMap().get("answer", String.class)).thenReturn("Answer 1");
//
//
//
//    }
//
//    @Test
//    void getFaqsList() {
//        MockitoAnnotations.initMocks(this);
//
//        when(faqs.hasChildren()).thenReturn(true);
//        when(faqs.getChildren()).thenReturn(Arrays.asList(childResource,childResource2));
//        when(childResource.getValueMap()).thenReturn(valueMap1);
//        when(childResource2.getValueMap()).thenReturn(valueMap2);
//        when(valueMap1.get("question", String.class)).thenReturn("Question 1");
//        when(valueMap1.get("answer", String.class)).thenReturn("Answer 1");
//        when(valueMap2.get("question", String.class)).thenReturn("Question 2");
//        when(valueMap2.get("answer", String.class)).thenReturn("Answer 2");
//
//        FaqModel faqModel = new FaqModel();
//        List<FaqEntity> faqsList = faqModel.getFaqsList();
//
////        assertEquals(2,faqsList.size());
//        assertEquals("weekend kab ayga", faqsList.get(0).getQuestion());
//        assertEquals("kal", faqsList.get(0).getAnswer());
//        assertEquals("hello", faqsList.get(1).getQuestion());
//        assertEquals("hey", faqsList.get(1).getAnswer());
//    }
//}