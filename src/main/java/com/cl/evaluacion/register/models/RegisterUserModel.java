package com.cl.evaluacion.register.models;

import com.cl.evaluacion.register.entities.PhoneEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor( access = AccessLevel.PRIVATE)
public class RegisterUserModel {
    private String name;
    private String email;
    private String password;
    private List<PhoneEntity> phones;
    public RegisterUserModel() {
        //ignore
    }
}
