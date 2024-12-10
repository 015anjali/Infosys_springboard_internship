package com.example.myapp.controller;

import com.example.myapp.model.MailData;
 import com.example.myapp.service.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

 @Autowired private EmailService emailService;

 // Sending a simple Email
 @PostMapping("/sendmail")
 public String
 sendMail(@RequestBody MailData details)
 {
     String status
         = emailService.sendSimpleMail(details);

     return status;
 }

 // Sending email with attachment
 @PostMapping("/attachmentmail")
 public String sendMailWithAttachment(
     @RequestBody MailData details)
 {
     String status
         = emailService.sendMailWithAttachment(details);

     return status;
 }
 @GetMapping("/test")
public String testEndpoint() {
    return "Server is accessible";
}


}
