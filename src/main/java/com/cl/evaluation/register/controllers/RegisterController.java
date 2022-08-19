package com.cl.evaluation.register.controllers;

import com.cl.evaluation.register.models.RegisterUserModel;
import com.cl.evaluation.register.models.ResponseModel;
import com.cl.evaluation.register.models.UserModel;
import com.cl.evaluation.register.services.ExceptionHandlerService;
import com.cl.evaluation.register.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseModel<UserModel> register(@RequestBody RegisterUserModel registerUserModel) {
        ResponseModel<UserModel> response = new ResponseModel<>();
        try {
            response.setData(registerService.registerUser(registerUserModel));
        } catch (Exception e) {
            response.setError(exceptionHandlerService.getError(e));
        }
        return response;
    }
    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}