package com.training.aem.core.models;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class ImageOrVideoModelTest {
    AemContext aemContext = new AemContext();

    @InjectMocks
    ImageOrVideoModel imageOrVideoModel;

    @BeforeEach
    void setUp() {
        aemContext.addModelsForClasses(ImageOrVideoModel.class);
    }

    @Test
    void getImage() {
       imageOrVideoModel.getImage();
    }

    @Test
    void getAssetType() {
        imageOrVideoModel.getAssetType();
    }

    @Test
    void getVideo() {
        imageOrVideoModel.getVideo();
    }
}