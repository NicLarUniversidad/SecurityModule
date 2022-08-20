package com.cl.evaluation.register.controllers;

import com.cl.evaluation.register.models.ErrorModel;
import com.cl.evaluation.register.models.LoginModel;
import com.cl.evaluation.register.models.ResponseModel;
import com.cl.evaluation.register.models.UserModel;
import com.cl.evaluation.register.services.LoginService;
import com.cl.evaluation.register.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

@RestController
public class LoginController {
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseModel<UserModel>> login(@RequestBody LoginModel loginModel) {
        var response = new ResponseModel<UserModel>();
        int statusCode = 200;
        try {
            var user = loginService.login(loginModel);
            response.setData(user);
        } catch (EntityNotFoundException e) {
            response.setError(e.getLocalizedMessage());
            statusCode = 400;
        }
        return ResponseEntity.status(statusCode).body(response);
    }
}
