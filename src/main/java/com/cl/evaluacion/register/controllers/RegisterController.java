package com.cl.evaluacion.register.controllers;

import com.cl.evaluacion.register.entities.UserEntity;
import com.cl.evaluacion.register.models.RegisterUserModel;
import com.cl.evaluacion.register.models.ResponseModel;
import com.cl.evaluacion.register.services.ExceptionHandlerService;
import com.cl.evaluacion.register.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    private final RegisterService registerService;
    private final ExceptionHandlerService exceptionHandlerService;

    @Autowired
    public RegisterController(RegisterService registerService,
                              ExceptionHandlerService exceptionHandlerService) {
        this.registerService = registerService;
        this.exceptionHandlerService = exceptionHandlerService;
    }

    @PostMapping("/register")
    public ResponseModel<UserEntity> register(RegisterUserModel registerUserModel) {
        ResponseModel<UserEntity> response = new ResponseModel<>();
        try {
            response.setData(registerService.registerUser(registerUserModel));
        } catch (Exception e) {
            response.setError(exceptionHandlerService.getError(e));
        }
        return response;
    }
}
