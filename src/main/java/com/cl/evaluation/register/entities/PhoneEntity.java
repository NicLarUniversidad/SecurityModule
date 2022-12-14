package com.cl.evaluation.register.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity(name = "phone")
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PhoneEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    private int number;
    @Column(name = "city_code")
    private int cityCode;
    @Column(name = "country_code")
    private int countryCode;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @ToString.Exclude
    @JsonBackReference
    private UserEntity userEntity;

    public PhoneEntity() {
        //needs for builder
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PhoneEntity that = (PhoneEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
