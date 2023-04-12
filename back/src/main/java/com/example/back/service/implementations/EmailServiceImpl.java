package com.example.back.service.implementations;

import com.example.back.service.interfaces.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service("emailService")
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String senderEmail;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public MimeMessage createMimeMessage() {
        return javaMailSender.createMimeMessage();
    }

    @Override
    public void sendEmail(SimpleMailMessage email) {
        javaMailSender.send(email);
    }

    @Override
    public void sendEmail(MimeMessage mimeMessage) {
        javaMailSender.send(mimeMessage);
    }

    @Override
    public void sendEmail(String to, String subject, String text) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(senderEmail);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
