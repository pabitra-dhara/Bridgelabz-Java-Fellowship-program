package com.fundoo.notificationservice.controller;

import com.fundoo.notificationservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MailTestController {

    private final EmailService emailService;

    @GetMapping("/mail-test")
    public String sendMail() {

        emailService.sendEmail(
                "pabitradhara096@gmail.com",
                "Test Mail",
                "Notification Service Working"
        );

        return "Mail Sent";
    }
}