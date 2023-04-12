package com.example.back.service.interfaces;

import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    MimeMessage createMimeMessage();
    void sendEmail(SimpleMailMessage email);
    void sendEmail(MimeMessage mimeMessage);
    void sendEmail(String to, String subject, String text);
}
