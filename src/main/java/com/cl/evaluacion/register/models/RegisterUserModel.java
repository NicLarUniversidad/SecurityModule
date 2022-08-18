package com.cl.evaluacion.register.models;

import lombok.Data;

import java.util.List;

@Data
public class RegisterUserModel {
    private String name;
    private String email;
    private String password;
    private List<PhoneEntity> phones;
}
