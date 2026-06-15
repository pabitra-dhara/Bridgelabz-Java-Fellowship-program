package com.fundoonotesapp.fundoo.controller;

import com.fundoonotesapp.fundoo.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MailTestController {

    private final EmailService emailService;

    @GetMapping("/mail-test")
    public String testMail() {

        emailService.sendEmail(
                "saket.pandey@bridgelabz.com",
                "Fundoo Notes Test",
                "Email configuration successful"
        );

        return "Mail Sent";
    }
}