package com.cl.evaluation.register.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Data
@Entity(name = "phone")
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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

    public PhoneEntity() {
        //needs for builder
    }
}
