package com.cl.evaluacion.register.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "UserEntity")
@Data
public class UserEntity {
    @Id
    private String id;
    private String email;
    private String password;
    private Date created;
    private Date modified;
    @Column(name = "last_login")
    private Date lastLogin;
    private String token;
    private boolean isActive;
}
