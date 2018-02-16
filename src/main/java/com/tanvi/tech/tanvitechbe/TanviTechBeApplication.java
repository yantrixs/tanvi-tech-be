package com.tanvi.tech.tanvitechbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class TanviTechBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TanviTechBeApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	/*@Bean
	public SimpleMailMessage mailMessage() {
		return new JavaMailSender();
	}*/
}
