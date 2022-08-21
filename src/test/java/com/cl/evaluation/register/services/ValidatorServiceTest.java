package com.cl.evaluation.register.services;

import com.cl.evaluation.register.ApplicationTest;
import com.cl.evaluation.register.expections.InvalidFormatException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidatorServiceTest extends ApplicationTest {

    private final ValidatorService validatorService;

    @Autowired
    public ValidatorServiceTest(ValidatorService validatorService) {
        this.validatorService = validatorService;
    }

    @Test
    void validatePasswordTest() {
        String validPassword = "123456";
        assertDoesNotThrow(() -> validatorService.validatePassword(validPassword));
    }

    @Test
    void invalidPasswordTest() {
        String invalidPasswordEmpty = "";

        InvalidFormatException exception = assertThrows(InvalidFormatException.class,
                () -> validatorService.validatePassword(invalidPasswordEmpty));
        assertThat(exception.getMessage(), equalTo(InvalidFormatException.VOID_PASSWORD));

        exception = assertThrows(InvalidFormatException.class,
                () -> validatorService.validatePassword(null));
        assertThat(exception.getMessage(), equalTo(InvalidFormatException.VOID_PASSWORD));
    }

    @Test
    void validateEmailTest() {
        String validEmail = "abc@example.com";
        assertDoesNotThrow(() -> validatorService.validateEmail(validEmail));
    }

    @Test
    void invalidEmailTest() {
        String invalidEmailEmpty = "";

        InvalidFormatException exception = assertThrows(InvalidFormatException.class,
                () -> validatorService.validateEmail(invalidEmailEmpty));
        assertThat(exception.getMessage(), equalTo(InvalidFormatException.VOID_MAIL));

        exception = assertThrows(InvalidFormatException.class,
                () -> validatorService.validateEmail(null));
        assertThat(exception.getMessage(), equalTo(InvalidFormatException.VOID_MAIL));
    }
}
