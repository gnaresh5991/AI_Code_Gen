package com.email.management.system.app.service;

import com.email.management.system.app.model.SentMails;
import com.email.management.system.app.repository.SentMailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    SentMailsRepository sentMailsRepository;

    public void sendEmails(List<String> recipients, String subject, String body) {
        for (String to : recipients) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            message.setFrom("gnaresh5991@gmail.com");
            mailSender.send(message);
            SentMails sentMails=new SentMails();
            sentMails.setEmail(to);
            sentMailsRepository.save(sentMails);

        }
    }
    public List<SentMails> getSentEmails() {
        return sentMailsRepository.findAll();
    }
}