package com.tanvi.tech.tanvitechbe.repository;

import com.tanvi.tech.tanvitechbe.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(final String userName);

    User findByEmail(final String email);

    User findByResetToken(String resetToken);
}
