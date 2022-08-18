package com.cl.evaluacion.register.models;

import lombok.Data;

@Data
public class PhoneEntity {
    private int number;
    private int cityCode;
    private int countryCode;
}
