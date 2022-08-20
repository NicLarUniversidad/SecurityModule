package com.cl.evaluation.register.services;

import com.cl.evaluation.register.expections.InvalidFormatException;
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
        String EMAIL_REGEX = "[_A-Za-z\\d-]+(\\.[_A-Za-z\\d-]+)*@[A-Za-z\\d-]+(\\.[A-Za-z\\d]+)*(\\.[A-Za-z]{2,})$";
        var formatValid = Pattern.matches(EMAIL_REGEX, email);
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
