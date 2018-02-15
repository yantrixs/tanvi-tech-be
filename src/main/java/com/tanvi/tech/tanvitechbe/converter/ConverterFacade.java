package com.tanvi.tech.tanvitechbe.converter;

import com.tanvi.tech.tanvitechbe.converter.factory.ConverterFactory;
import com.tanvi.tech.tanvitechbe.dto.UserDTO;
import com.tanvi.tech.tanvitechbe.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ConverterFacade {

    @Autowired
    private ConverterFactory converterFactory;

    public User convert(final UserDTO dto) {
        return (User) converterFactory.getConverter(dto.getClass()).convert(dto);
    }
}
