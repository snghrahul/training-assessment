package com.training.aem.core.services.jobs;

import com.day.cq.mailer.MessageGateway;
import com.day.cq.mailer.MessageGatewayService;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.sling.event.jobs.Job;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class SendEmailNotificationJobTest {
    AemContext aemContext = new AemContext();
    @Mock
    Job job;
    @InjectMocks
    SendEmailNotificationJob sendEmailNotificationJob;
    @Mock
    MessageGatewayService messageGatewayService;
    @Mock
    MessageGateway<Email> messageGateway;
    @Mock
    Email email;

    @BeforeEach
    void setUp() {
        aemContext.addModelsForClasses(SendEmailNotificationJob.class);
    }

    @Test
    void process() throws EmailException {
        sendEmailNotificationJob.process(job);
    }

    @Test
    void sendEmail() throws EmailException {
        when(messageGatewayService.getGateway(Email.class)).thenReturn(messageGateway);
        sendEmailNotificationJob.sendEmail("New Page Created","A new page has been created : ","snghrahul09@gmail.com");
    }
}