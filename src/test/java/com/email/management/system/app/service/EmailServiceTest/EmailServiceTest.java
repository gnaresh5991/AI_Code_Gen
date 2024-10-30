package com.email.management.system.app.service.EmailServiceTest;

import com.email.management.system.app.model.SentMails;
import com.email.management.system.app.repository.SentMailsRepository;
import com.email.management.system.app.service.EmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class EmailServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @Mock
    private SentMailsRepository sentMailsRepository;

    @InjectMocks
    private EmailService emailService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSendEmails() {
        List<String> recipients = Arrays.asList("a@b.com", "b@c.com");
        String subject = "Test Email";
        String body = "Test Email Body";

        emailService.sendEmails(recipients, subject, body);

        verify(mailSender, times(recipients.size())).send(any(SimpleMailMessage.class));
        verify(sentMailsRepository, times(recipients.size())).save(any(SentMails.class));
    }
}
//<<<<<<<  947b97db-c20a-4012-882f-8ac7505788b2  >>>>>>>

