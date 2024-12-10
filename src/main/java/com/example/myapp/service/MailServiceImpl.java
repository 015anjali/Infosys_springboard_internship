package com.example.myapp.service;

import com.example.myapp.model.MailData;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class MailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

        @Override
public String sendSimpleMail(MailData details) {
    try {
        System.out.println("Preparing to send email to: " + details.getRecipient());
        System.out.println("Email subject: " + details.getSubject());
        System.out.println("Email body: " + details.getMsgBody());
        
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(sender);
        mailMessage.setTo(details.getRecipient());
        mailMessage.setText(details.getMsgBody());
        mailMessage.setSubject(details.getSubject());
        
        javaMailSender.send(mailMessage);
        System.out.println("Email sent successfully to: " + details.getRecipient());
        
        return "Mail Sent Successfully...";
    } catch (Exception e) {
        System.err.println("Error while sending email: " + e.getMessage());
        return "Error while Sending Mail: " + e.getMessage();
    }
}

    @Override
public String sendMailWithAttachment(MailData details) {
    try {
        System.out.println("Preparing to send email with attachment to: " + details.getRecipient());
        System.out.println("Email subject: " + details.getSubject());
        System.out.println("Email body: " + details.getMsgBody());
        System.out.println("Attachment path: " + details.getAttachment());

        File file = new File(details.getAttachment());
        if (!file.exists()) {
            System.err.println("File not found: " + details.getAttachment());
            return "Attachment file not found.";
        }
        System.out.println("Attachment file size: " + file.length() + " bytes");

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setTo(details.getRecipient());
        mimeMessageHelper.setText(details.getMsgBody());
        mimeMessageHelper.setSubject(details.getSubject());

        // Adding the attachment
        FileSystemResource fileResource = new FileSystemResource(file);
        mimeMessageHelper.addAttachment(fileResource.getFilename(), fileResource);

        javaMailSender.send(mimeMessage);
        System.out.println("Email with attachment sent successfully.");
        
        return "Mail sent Successfully";
    } catch (MessagingException e) {
        System.err.println("Error while sending email: " + e.getMessage());
        return "Error while sending mail: " + e.getMessage();
    } catch (Exception e) {
        System.err.println("Unexpected error: " + e.getMessage());
        return "Unexpected error occurred: " + e.getMessage();
    }
}

}
