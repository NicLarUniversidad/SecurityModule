package com.cl.evaluation.register.services;

import com.cl.evaluation.register.converters.UserEntityToUserModelConverter;
import com.cl.evaluation.register.models.LoginModel;
import com.cl.evaluation.register.models.UserModel;
import com.cl.evaluation.register.repositories.UserRepository;
import com.cl.evaluation.register.services.database.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UserService userService;
    private final UserEntityToUserModelConverter converter;
    private final TokenService tokenService;


    @Autowired
    public LoginService(UserService userService,
                        UserEntityToUserModelConverter converter,
                        TokenService tokenService) {
        this.userService = userService;
        this.converter = converter;
        this.tokenService = tokenService;
    }

    public UserModel login(LoginModel loginModel) {
        var user = userService.findByEmail(loginModel.getEmail());
        String token = tokenService.tokenize(loginModel.getEmail());
        userService.updateToken(user, token);
        return converter.convert(user);
    }
}
