package com.cl.evaluation.register.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity(name = "user_entity")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    private String email;
    private String name;
    private String password;
    @OneToMany(cascade = CascadeType.DETACH)
    @JsonManagedReference
    @ToString.Exclude
    private List<PhoneEntity> phones;
    private Timestamp created;
    private Timestamp modified;
    @Column(name = "last_login")
    private Timestamp lastLogin;
    @Column(length = 512)
    private String token;
    @Column(name = "active")
    private boolean isActive;

    public UserEntity() {
        //needs for builder
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserEntity that = (UserEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
