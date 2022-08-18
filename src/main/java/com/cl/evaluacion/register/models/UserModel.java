package com.cl.evaluacion.register.models;

import java.util.Date;
import lombok.Data;

@Data
public class UserModel {
    private String id;
    private String email;
    private Date created;
    private Date modified;
    private Date lastLogin;
    private String token;
    private boolean isActive;
}
