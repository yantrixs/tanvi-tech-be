package com.tanvi.tech.tanvitechbe.security.service;

import com.tanvi.tech.tanvitechbe.exception.model.ServiceException;
import com.tanvi.tech.tanvitechbe.model.User;
import io.jsonwebtoken.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


@Service
public class JsonWebTokenService implements TokenService {
    private static final Logger logger = Logger.getLogger(JsonWebTokenService.class);
    @Value("security.token.secret.key")
    private String tokenKey;

    private final UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public JsonWebTokenService(final UserDetailsService userDetailsService,
                               final PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String getToken(final String username, final String password) {
        if (username == null || password == null) {
            return null;
        }
        final User user = (User) userDetailsService.loadUserByUsername(username);
        Map<String, Object> tokenData = new HashMap<>();
        boolean isPasswordMatch = isPasswordMatched(password, user.getPassword());
        if (isPasswordMatch) {
            tokenData.put("clientType", "user");
            tokenData.put("userID", user.getId());
            tokenData.put("username", user.getUsername());
            tokenData.put("email", user.getEmail());
            tokenData.put("dob", user.getDob());
            tokenData.put("token_create_date", LocalDateTime.now());
            Calendar calendar = Calendar.getInstance();
            int tokenExpirationTime = 30;
            calendar.add(Calendar.MINUTE, tokenExpirationTime);
            tokenData.put("token_expiration_date", calendar.getTime());
            JwtBuilder jwtBuilder = Jwts.builder();
            jwtBuilder.setExpiration(calendar.getTime());
            jwtBuilder.setClaims(tokenData);
            return jwtBuilder.signWith(SignatureAlgorithm.HS512, tokenKey).compact();

        } else {
            throw new ServiceException("Authentication error", this.getClass().getName());
        }
    }

    private boolean isPasswordMatched(String userPassword, String dbPassword) {
        return passwordEncoder.matches(userPassword, dbPassword);
    }


    public Jws<Claims> tokenParser(String token) {
        Jws<Claims> claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(tokenKey)
                    .parseClaimsJws(token);
            logger.debug("debug claims is  " + claims);
        } catch (MissingClaimException | IncorrectClaimException e) {
            logger.debug(e.getMessage());
        }

        return claims;
    }
   /* public static void setTokenExpirationTime(final int tokenExpirationTime) {
        JsonWebTokenService.tokenExpirationTime = tokenExpirationTime;
    }*/
}
