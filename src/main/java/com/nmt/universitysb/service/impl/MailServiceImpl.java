package com.nmt.universitysb.service.impl;
import com.nmt.universitysb.controller.ApiStudentController;
import com.nmt.universitysb.requests.MailRequest;
import com.nmt.universitysb.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender mailSender;
    private static final Logger logger = LoggerFactory.getLogger(ApiStudentController.class);


    @Override
    @Async
    public void sendMailToStudent(MailRequest mailRequest) {
        logger.debug("RUN JOB: sendMailToEachStudent");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailRequest.getRecipients());
        message.setFrom(mailRequest.getFrom());
        message.setSubject(mailRequest.getSubject());
        message.setText(mailRequest.getBody());

        mailSender.send(message);
        logger.debug("END JOB: sendMailToEachPersonGroup");
    }

}
