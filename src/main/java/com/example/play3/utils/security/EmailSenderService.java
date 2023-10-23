package com.example.play3.utils.security;

import com.example.play3.domain.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;

    private static final String SENDER_EMAIL = "hvacrawler2023@gmail.com";
    private static final String SENDER_NAME = "HVA Crawler Team";

    public void sendVerificationEmail(String url, User theUser)
            throws MessagingException, UnsupportedEncodingException {
        String subject = "Email Verification";
        String mailContent = "<p> Hi new Crawler, </p>" +
                "<p> Thank you for registering with us, " +
                "Please, follow the link below to complete your registration. </p>" +
                "<a href=\"" + url+ "\">Verify your email to activate your account</a>" +
                "<p> Thank you, <br> HVA Crawler Team </p>";
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom(SENDER_EMAIL, SENDER_NAME);
        messageHelper.setTo(theUser.getEmail());
        messageHelper.setSubject(subject);
        messageHelper.setText(mailContent, true);
        mailSender.send(message);
    }
}
