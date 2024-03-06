package com.training.aem.core.models.impl;

import com.training.aem.core.services.impl.ContentFragmentServiceImpl;
import com.training.aem.core.services.impl.SortedProductServiceImpl;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.LoginException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class ContentFragmentModelImplTest {

    AemContext aemContext;

    @InjectMocks
    @Spy
    ContentFragmentModelImpl contentFragmentModel;
    @Mock
    ContentFragmentServiceImpl contentFragmentService;

    @BeforeEach
    void setUp() {
        aemContext.addModelsForClasses(ContentFragmentServiceImpl.class);
    }
    @Test
    void getAllContentFragment() throws LoginException {
        contentFragmentModel.getAllContentFragments();
    }
}