package com.example.myapp.service;

import com.example.myapp.model.MailData;

public interface EmailService {

    // To send a simple email
    String sendSimpleMail(MailData details);

    // To send an email with attachment
    String sendMailWithAttachment(MailData details);
}