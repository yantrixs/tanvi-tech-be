package com.tanvi.tech.tanvitechbe.service;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    void sendEmail(SimpleMailMessage email);
    boolean findByResetToken(String resetToken);
}
