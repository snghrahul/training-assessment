package com.training.aem.core.servlets;

import com.day.cq.mailer.MessageGateway;
import com.day.cq.mailer.MessageGatewayService;
import com.training.aem.core.services.jobs.SendEmailNotificationJob;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = {Servlet.class},
        property = { "sling.servlet.paths="+"/bin/sendemail",
                "sling.servlet.methods=" + HttpConstants.METHOD_GET
        }
)
public class SendMailServlet extends SlingAllMethodsServlet {
    private static Logger log = LoggerFactory.getLogger(SendEmailNotificationJob.class);

    @Reference
    MessageGatewayService messageGatewayService;

//    @Reference
//    SendEmailNotificationJob sendEmailNotificationJob;
    @Override
    protected void doGet(SlingHttpServletRequest request,SlingHttpServletResponse response) throws ServletException, IOException {
        String adminEmail = "snghrahul09@gmail.com";

        String subject = "New Page Created";
        String msgBody = "A new page has been created : ";
        HtmlEmail email = new HtmlEmail();
        try {
            email.addTo(adminEmail);
        } catch (EmailException e) {
            throw new RuntimeException(e);
        }
        email.setSubject(subject);
        try {
            email.setMsg(msgBody);
        } catch (EmailException e) {
            throw new RuntimeException(e);
        }
        MessageGateway<HtmlEmail> messageGateway = messageGatewayService.getGateway(HtmlEmail.class);
        if(messageGateway != null){
            log.debug("sending out email");
            messageGateway.send(email);

        }else {
            log.error("The message gateway could not be retrieved");
        }


        response.getWriter().write("heyy");


    }
}
