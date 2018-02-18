package com.tanvi.tech.tanvitechbe.service;

import com.tanvi.tech.tanvitechbe.model.User;
import com.tanvi.tech.tanvitechbe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Service
public class BasicUserService implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Autowired
    public BasicUserService(final UserRepository repository,
                            final PasswordEncoder passwordEncoder,
                            final EmailService emailService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    @Override
    public User create(final User user) {
        user.setCreatedAt(String.valueOf(LocalDateTime.now()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @Override
    public User find(final String id) {
        return repository.findOne(id);
    }

    @Override
    public User findByUsername(final String userName) {
        return repository.findByUsername(userName);
    }

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public User findByResetToken(String resetToken) {
        return repository.findByResetToken(resetToken);
    }

    @Override
    public void saveResetToken(User user, String apiUrl) {
        user.setResetToken(UUID.randomUUID().toString());
        repository.save(user);

        // Email message --> move to properties file
        SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
        passwordResetEmail.setFrom("vsfoodjumbo@gmail.com");
        passwordResetEmail.setTo(user.getEmail());
        passwordResetEmail.setSubject("Password Reset Request");
        passwordResetEmail.setText("To reset your password, click the link below:\n" + apiUrl
                + "/auth/reset?token=" + user.getResetToken());

        emailService.sendEmail(passwordResetEmail);
    }

    @Override
    public void saveResetPassword(User user) {
        user.setUpdatedAt(String.valueOf(LocalDateTime.now()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setResetToken("");
        repository.save(user);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User update(final String id, final User user) {
        user.setId(id);

        final User saved = repository.findOne(id);

        if (saved != null) {
            user.setCreatedAt(saved.getCreatedAt());
            user.setUpdatedAt(String.valueOf(LocalDateTime.now()));
        } else {
            user.setCreatedAt(String.valueOf(LocalDateTime.now()));
        }
        repository.save(user);
        return user;
    }

    @Override
    public String delete(final String id) {
        repository.delete(id);
        return id;
    }

}
