package com.cl.evaluation.register.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

@Entity(name = "UserEntity")
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    @Pattern(regexp = ".*@.*\\..*", message = "Formato de mail inválido")
    private String email;
    private String name;
    private String password;
    @OneToMany(cascade = CascadeType.DETACH)
    @JsonManagedReference
    private List<PhoneEntity> phones;
    private Date created;
    private Date modified;
    @Column(name = "last_login")
    private Date lastLogin;
    private String token;
    @Column(name = "active")
    private boolean isActive;

    public UserEntity() {
        //needs for builder
    }
}