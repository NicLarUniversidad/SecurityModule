package com.cl.evaluation.register.services;

import com.cl.evaluation.register.ApplicationTest;
import com.cl.evaluation.register.expections.InvalidFormatException;
import com.cl.evaluation.register.expections.UserAlreadyExistsException;
import com.cl.evaluation.register.models.LoginModel;
import com.cl.evaluation.register.models.RegisterUserModel;
import com.cl.evaluation.register.models.UserModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.security.auth.login.LoginException;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class LoginServiceTest extends ApplicationTest {

    private final LoginService loginService;
    private final RegisterService registerService;

    @Autowired
    public LoginServiceTest(LoginService loginService,
                            RegisterService registerService) {
        this.loginService = loginService;
        this.registerService = registerService;
    }

    private LoginModel loginModel;

    @BeforeEach
    public void beforeAll() {
            var registerUserModel = new RegisterUserModel();
            registerUserModel.setEmail("aabc@example.com");
            registerUserModel.setName("Prueba");
            registerUserModel.setPassword("123");

            loginModel = new LoginModel();
            loginModel.setEmail(registerUserModel.getEmail());
            loginModel.setPassword(registerUserModel.getPassword());
            try {
                this.registerService.registerUser(registerUserModel);
            } catch (InvalidFormatException | UserAlreadyExistsException e) {
                //ignore, user already exists
            }
    }


    @Test
    public void loginTest() throws LoginException {
        var user = loginService.login(loginModel);
        assertThat(user.getToken(), notNullValue());
    }

    @Test
    public void validateTokenTest() throws LoginException {
        var user = loginService.login(loginModel);
        assertDoesNotThrow(() -> loginService.validateToken("Bearer " + user.getToken()));
    }
}
