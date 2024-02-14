package com.training.aem.core.services.jobs;


import com.day.cq.mailer.MessageGateway;
import com.day.cq.mailer.MessageGatewayService;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.sling.event.jobs.Job;
import org.apache.sling.event.jobs.consumer.JobConsumer;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = {JobConsumer.class},
property = {JobConsumer.PROPERTY_TOPICS + " = " + "com/training-project"})
public class SendEmailNotificationJob implements JobConsumer{

    private static Logger log = LoggerFactory.getLogger(SendEmailNotificationJob.class);

    @Reference
    MessageGatewayService messageGatewayService;
    @Override
    public JobResult process(Job job) {
        String pagePath = (String) job.getProperty("pagePath");
        String pageTitle = (String) job.getProperty("pageTitle");
        String adminEmail = "snghrahul09@gmail.com";

        String subject = "New Page Created";
        String msgBody = "A new page has been created : ";
        try {
            sendEmail(subject,msgBody,adminEmail);
        } catch (EmailException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
    public void sendEmail(String subject, String msgBody, String adminEmail) throws EmailException {
        Email email = new HtmlEmail();
        email.addTo(adminEmail);
        email.setSubject(subject);
        email.setMsg(msgBody);
        MessageGateway<Email> messageGateway = messageGatewayService.getGateway(HtmlEmail.class);
        if(messageGateway != null){
            log.debug("sending out email");
            messageGateway.send((Email) email);

        }else {
            log.error("The message gateway could not be retrieved");
        }

    }
}
