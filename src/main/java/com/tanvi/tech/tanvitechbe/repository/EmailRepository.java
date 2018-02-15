package com.tanvi.tech.tanvitechbe.repository;

import com.tanvi.tech.tanvitechbe.model.ResetPassword;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.mail.SimpleMailMessage;

public interface EmailRepository extends MongoRepository<ResetPassword, String> {
    void sendEmail(SimpleMailMessage email);
    boolean findByResetToken(String resetToken);
}
