package com.tanvi.tech.tanvitechbe.controller;

import com.tanvi.tech.tanvitechbe.dto.LoginDTO;
import com.tanvi.tech.tanvitechbe.dto.TokenDTO;
import com.tanvi.tech.tanvitechbe.model.User;
import com.tanvi.tech.tanvitechbe.security.service.TokenService;
import com.tanvi.tech.tanvitechbe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/")
public class AuthenticationController {
    private final TokenService tokenService;
    private final UserService userService;

    @Autowired
    public AuthenticationController(final TokenService tokenService,
                                    final UserService userService) {
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @RequestMapping(value = "auth/login", method = RequestMethod.POST)
    public ResponseEntity<?> authenticate(@RequestBody final LoginDTO dto) {
        final String token = tokenService.getToken(dto.getUsername(), dto.getPassword());
        if (token != null) {
            final TokenDTO response = new TokenDTO();
            response.setToken(token);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Authentication failed", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "auth/forgot", method = RequestMethod.POST)
    public ResponseEntity<?> forgotPassword(@RequestBody final Map<String, String> email,
                                            HttpServletRequest request) {
        String emailAdd = email.get("email");
        User userExist = userService.findByEmail(emailAdd);
        if (userExist != null) {
            String appUrl = request.getScheme() + "://" + request.getServerName() + ":4200";
            userService.saveResetToken(userExist, appUrl);
            return new ResponseEntity<>("Email sent", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Email does not exit", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "auth/reset", method = RequestMethod.POST)
    public ResponseEntity<?> resetPassword(@RequestBody final Map<String, String> password ) {
        User resetUser = userService.findByResetToken(password.get("token"));
        if(resetUser != null) {
            resetUser.setPassword(password.get("password"));
            userService.saveResetPassword(resetUser);
            return new ResponseEntity<>("Password changed successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("invalid token", HttpStatus.BAD_REQUEST);
        }
    }
}
