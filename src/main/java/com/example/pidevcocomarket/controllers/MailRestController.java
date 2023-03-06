package com.example.pidevcocomarket.controllers;

import com.example.pidevcocomarket.entities.Mail;
import com.example.pidevcocomarket.services.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/mail")
public class MailRestController {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private EmailServiceImpl emailService;

    @PostMapping("/send")
    public void sendMail(@RequestParam("from") String from,@RequestParam("to") String to,
                         @RequestParam("subject") String subject, @RequestParam("body") String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
        System.out.println("Mail Sent successfully !! ");
    }
    @GetMapping("/receive")
    public void receiveMail() {
        emailService.receiveMail();
    }
}
