package com.fundoonotesapp.fundoo.service;

public interface EmailService {
    void sendEmail(String to, String subject, String body);
}