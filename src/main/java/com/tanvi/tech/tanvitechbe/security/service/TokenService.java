package com.tanvi.tech.tanvitechbe.security.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public interface TokenService {

    String getToken(String username, String password);

    Jws<Claims> tokenParser(String token);
}
