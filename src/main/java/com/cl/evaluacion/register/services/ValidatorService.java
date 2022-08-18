package com.cl.evaluacion.register.services;

import com.cl.evaluacion.register.expections.InvalidFormatException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

import static org.apache.logging.log4j.util.Strings.isBlank;

@Service
public class ValidatorService {
    @Value("${com.cl.evaluation.register.validation.password.regex:.*}")
    private String passwordRegex;
    public void validateEmail(String email) throws InvalidFormatException {
        if (isBlank(email))
            throw new InvalidFormatException(InvalidFormatException.VOID_MAIL);
        var formatValid = Pattern.matches(".*@.*\\..*", email);
        if (!formatValid)
            throw new InvalidFormatException(InvalidFormatException.INVALID_MAIL_FORMAT);
    }
    public void validatePassword(String password) throws InvalidFormatException {
        if (isBlank(password))
            throw new InvalidFormatException(InvalidFormatException.VOID_PASSWORD);
        var formatValid = Pattern.matches(passwordRegex, password);
        if (!formatValid)
            throw new InvalidFormatException(InvalidFormatException.INVALID_PASSWORD_FORMAT);
    }
}
