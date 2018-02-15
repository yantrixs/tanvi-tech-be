package com.tanvi.tech.tanvitechbe.converter.dto;

import com.tanvi.tech.tanvitechbe.dto.UserDTO;
import com.tanvi.tech.tanvitechbe.model.Authority;
import com.tanvi.tech.tanvitechbe.model.User;
import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class UserDTOConverter implements Converter<UserDTO, User> {

    @Override
    public User convert(final UserDTO dto) {
        final User user = new User();

        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setAccountNonExpired(false);
        user.setCredentialsNonExpired(false);
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setDisplayName(dto.getFirstName() + ' ' + dto.getLastName());
        user.setGender(dto.getGender());
        user.setMobileNumber(dto.getMobileNumber());
        LocalDate birthDate = convertStringToDate(dto.getDob());
        user.setDob(birthDate);
        user.setEnabled(true);

        List<Authority> authorities = new ArrayList<>();
        authorities.add(Authority.ROLE_USER);
        user.setAuthorities(authorities);
        return user;
    }

    private LocalDate convertStringToDate(String dob)  {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
            return LocalDate.parse(dob, formatter);
        } catch (DateTimeParseException exc) {
            System.out.printf("%s is not parsable!%n", dob);
            throw exc;      // Rethrow the exception.
        }
    }
}
