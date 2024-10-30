package com.email.management.system.app.controller;

import com.email.management.system.app.model.SentMails;
import com.email.management.system.app.model.Vendor;
import com.email.management.system.app.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/api/emails")
public class EmailController {

    @Autowired
    private EmailService emailService;
    @PostMapping("/sendEmailtoVendors")
    public void sendEmails(@RequestBody List<Vendor> vendors) {
        List<String> recipients = vendors.stream()
                .map(Vendor::getEmail)
                .collect(Collectors.toList());
        String subject = "Vendor Payment";

        for (Vendor vendor : vendors) {
            String body = String.format("Sending payments to vendor %s at UPI %s", vendor.getName(), vendor.getUpi());
            try {
                emailService.sendEmails(recipients, subject, body);
            } catch (Exception e) {
                System.err.println("Error sending email to " + vendor.getEmail() + ": " + e.getMessage());
            }
        }

    }
    @GetMapping("/sent")
    public List<SentMails> getSentEmails() {
        return emailService.getSentEmails();
    }
}

