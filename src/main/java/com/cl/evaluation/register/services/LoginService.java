package com.cl.evaluation.register.services;

import com.cl.evaluation.register.converters.UserEntityToUserModelConverter;
import com.cl.evaluation.register.entities.UserEntity;
import com.cl.evaluation.register.expections.AuthenticationException;
import com.cl.evaluation.register.models.LoginModel;
import com.cl.evaluation.register.models.UserModel;
import com.cl.evaluation.register.repositories.UserRepository;
import com.cl.evaluation.register.services.database.UserService;
import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.security.auth.login.LoginException;

@Service
public class LoginService {
    public static String LOGIN_FAILED = "Contraseña incorrecta";
    public static String USER_NOT_FOUND = "Usuario no registrado";

    private final UserService userService;
    private final UserEntityToUserModelConverter converter;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public LoginService(UserService userService,
                        UserEntityToUserModelConverter converter,
                        TokenService tokenService,
                        PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.converter = converter;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
    }

    public UserModel login(LoginModel loginModel) throws LoginException {
        var user = userService.findByEmail(loginModel.getEmail());
        if (user == null)
            throw new EntityNotFoundException(USER_NOT_FOUND);
        verifyPassword(user, loginModel);
        String token = tokenService.tokenize(loginModel.getEmail());
        userService.updateToken(user, token);
        return converter.convert(user);
    }

    private void verifyPassword(UserEntity user, LoginModel loginModel) throws LoginException {
        if (!passwordEncoder.matches(loginModel.getPassword(), user.getPassword()))
            throw new LoginException(LOGIN_FAILED);
    }

    public UserModel validateToken(String token) throws AuthenticationException, JsonParseException {
        var jws = tokenService.validateToken(token);
        var user = userService.findByEmail(jws.getBody().getSubject());
        if (user == null)
            throw new AuthenticationException(AuthenticationException.USER_NOT_FOUND);
        return converter.convert(user);
    }
}
