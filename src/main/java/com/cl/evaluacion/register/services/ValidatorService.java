package com.cl.evaluacion.register.services;

import com.cl.evaluacion.register.expections.InvalidFormatException;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class ValidatorService {
    public void validateEmail(String email) throws InvalidFormatException {
        if (isNull(email))
            throw new InvalidFormatException("El campo de email está vacío");
    }
}
