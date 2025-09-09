package com.example.SmartTaskManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void sendTaskMail(String toEmail,String taskTitle,String taskDescription){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(taskTitle);
        message.setText("Mail Sent Successfully "+taskDescription);
        mailSender.send(message);
    }
}
