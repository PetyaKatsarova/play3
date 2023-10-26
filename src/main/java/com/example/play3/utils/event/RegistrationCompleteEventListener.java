package com.example.play3.utils.event;

import com.example.play3.domain.User;
import com.example.play3.service.RegistrationService;
import com.example.play3.utils.security.EmailSenderService;
import jakarta.mail.MessagingException;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    private final RegistrationService   registrationService;
    private final EmailSenderService    emailSenderService;

    public RegistrationCompleteEventListener(RegistrationService registrationService, EmailSenderService emailSenderService) {
        this.registrationService = registrationService;
        this.emailSenderService = emailSenderService;
    }

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        User theUser = event.getUser();
        String url = event.getApplicationUrl() + "/verifyEmail?token=" + theUser.getVerificationToken();
        try {
            emailSenderService.sendVerificationEmail(url, theUser);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException("Error sending verification email", e);
        }
    }
}
