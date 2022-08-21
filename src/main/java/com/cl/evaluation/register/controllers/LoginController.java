package com.cl.evaluation.register.controllers;

import com.cl.evaluation.register.expections.AuthenticationException;
import com.cl.evaluation.register.models.LoginModel;
import com.cl.evaluation.register.models.ResponseModel;
import com.cl.evaluation.register.models.UserModel;
import com.cl.evaluation.register.services.LoginService;
import com.fasterxml.jackson.core.JsonParseException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.security.auth.login.LoginException;

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
        } catch (EntityNotFoundException | LoginException e) {
            response.setError(e.getLocalizedMessage());
            statusCode = 400;
        }
        return ResponseEntity.status(statusCode).body(response);
    }

    @GetMapping("/validate-session")
    public ResponseEntity<ResponseModel<UserModel>> login(@RequestHeader("Authorization") String bearer) {
        var response = new ResponseModel<UserModel>();
        int statusCode = 200;
        try {
            var user = loginService.validateToken(bearer);
            response.setData(user);
        } catch (AuthenticationException | JsonParseException | SignatureException | ExpiredJwtException e) {
            response.setError(e.getLocalizedMessage());
            statusCode = 400;
        }
        return ResponseEntity.status(statusCode).body(response);
    }
}
