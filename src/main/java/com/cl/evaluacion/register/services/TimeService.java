package com.cl.evaluacion.register.services;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TimeService {
    public Date getCurrentTime() {
        return new Date();
    }
}
