package com.cl.evaluacion.register.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity(name = "phone")
public class PhoneEntity {
    @Id
    private int number;
    @Column(name = "city_code")
    private int cityCode;
    @Column(name = "country_code")
    private int countryCode;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @ToString.Exclude
    @JsonBackReference
    private UserEntity userEntity;
}
