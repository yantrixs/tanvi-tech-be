package com.tanvi.tech.tanvitechbe.security.service;


public interface TokenService {

    String getToken(String username, String password);
}
