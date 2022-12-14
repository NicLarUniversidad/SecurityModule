package com.cl.evaluation.register.models;

import java.util.Date;
import java.util.List;

import com.cl.evaluation.register.entities.PhoneEntity;
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
    private List<PhoneEntity> phones;
}
