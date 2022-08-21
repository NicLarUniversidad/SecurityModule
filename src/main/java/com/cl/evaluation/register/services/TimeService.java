package com.cl.evaluation.register.services;

import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class TimeService {
    public Date getCurrentTime() {
        return new Date();
    }

    public Date getTime(int milliseconds) {
        return new Date(System.currentTimeMillis() + milliseconds);
    }

    public Timestamp toTimestamp(Date date) {
        return new Timestamp(date.getTime());
    }
    public Timestamp getCurrentTimestamp() {
        return this.toTimestamp(this.getCurrentTime());
    }
}
