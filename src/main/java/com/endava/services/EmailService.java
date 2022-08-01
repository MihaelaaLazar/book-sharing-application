package com.endava.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;
import java.util.Objects;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment env;

    @Autowired
    private SpringTemplateEngine templateEngine;

    public void sendConfirmationEmail(String body,
                                      String toEmail,
                                      String subject) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        mimeMessageHelper.setFrom(Objects.requireNonNull(env.getProperty("spring.mail.username")));
        mimeMessageHelper.setText(body, true);
        mimeMessageHelper.setTo(toEmail);
        mimeMessageHelper.setSubject(subject);
        javaMailSender.send(mimeMessage);
    }

    public void sendEmailWithThymeleaf(String to, String subject, Map<String, Object> template) throws MessagingException {
        Context context = new Context();
        context.setVariables(template);
        String htmlBody = templateEngine.process("emailTemplate.html", context);
        sendConfirmationEmail(htmlBody, to, subject);
    }
}
