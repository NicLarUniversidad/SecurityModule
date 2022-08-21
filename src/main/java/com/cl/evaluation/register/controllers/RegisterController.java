package com.cl.evaluation.register.controllers;

import com.cl.evaluation.register.expections.InvalidFormatException;
import com.cl.evaluation.register.expections.UserAlreadyExistsException;
import com.cl.evaluation.register.models.RegisterUserModel;
import com.cl.evaluation.register.models.ResponseModel;
import com.cl.evaluation.register.models.UserModel;
import com.cl.evaluation.register.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    private final RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    /*@GetMapping("/get-all")
    public ResponseEntity<Iterable<UserEntity>> getAll() {
        return ResponseEntity.status(200).body(this.registerService.getAllUser());
    }*/

    @PostMapping("/register")
    public ResponseEntity<ResponseModel<UserModel>> register(@RequestBody RegisterUserModel registerUserModel) {
        ResponseModel<UserModel> response = new ResponseModel<>();
        int status = 200;
        try {
            response.setData(registerService.registerUser(registerUserModel));
        } catch (InvalidFormatException | UserAlreadyExistsException e) {
            response.setError(e.getLocalizedMessage());
            status = 400;
        }
        return ResponseEntity.status(status).body(response);
    }
}
