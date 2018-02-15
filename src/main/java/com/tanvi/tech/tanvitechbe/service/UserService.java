package com.tanvi.tech.tanvitechbe.service;


import com.tanvi.tech.tanvitechbe.model.User;

import java.util.List;


public interface UserService {

    User create(User object);

    User find(String id);

    User findByUsername(String userName);

    User findByEmail(String email);

    User findByResetToken(String resetToken);

    void saveResetToken(User user, String apiUrl);

    void saveResetPassword(User user);

    List<User> findAll();

    User update(String id, User object);

    String delete(String id);
}
