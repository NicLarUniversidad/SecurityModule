package com.cl.evaluation.register.controllers;

import com.cl.evaluation.register.expections.AuthenticationException;
import com.cl.evaluation.register.models.ErrorModel;
import com.cl.evaluation.register.models.LoginModel;
import com.cl.evaluation.register.models.ResponseModel;
import com.cl.evaluation.register.models.UserModel;
import com.cl.evaluation.register.services.LoginService;
import com.cl.evaluation.register.services.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.security.auth.login.LoginException;

@RestController
public class LoginController {
    private final LoginService loginService;
    private final TokenService tokenService;

    @Autowired
    public LoginController(LoginService loginService,
                           TokenService tokenService) {
        this.loginService = loginService;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseModel<UserModel>> login(@RequestBody LoginModel loginModel) {
        var response = new ResponseModel<UserModel>();
        int statusCode = 200;
        try {
            var user = loginService.login(loginModel);
            response.setData(user);
        } catch (EntityNotFoundException | LoginException e) {
            response.setError(e.getLocalizedMessage());
            statusCode = 400;
        }
        return ResponseEntity.status(statusCode).body(response);
    }

    @PostMapping("/validate-session")
    public ResponseEntity<ResponseModel<UserModel>> login(@RequestBody String bearer) {
        var response = new ResponseModel<UserModel>();
        int statusCode = 200;
        try {
            var user = loginService.validateToken(bearer);
            response.setData(user);
        } catch (AuthenticationException e) {
            response.setError(e.getLocalizedMessage());
            statusCode = 400;
        }
        return ResponseEntity.status(statusCode).body(response);
    }
}
