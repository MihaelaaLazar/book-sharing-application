package com.endava.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Objects;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment env;

    public void sendConfirmationEmail(String body,
                                      String toEmail,
                                      String subject) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setFrom(Objects.requireNonNull(env.getProperty("spring.mail.username")));
        mimeMessageHelper.setText(body);
        mimeMessageHelper.setTo(toEmail);
        mimeMessageHelper.setSubject(subject);
        javaMailSender.send(mimeMessage);
    }

}
