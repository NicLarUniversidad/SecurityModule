package com.cl.evaluation.register.services;

import com.cl.evaluation.register.ApplicationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class TimeServiceTest extends ApplicationTest {

    private final TimeService timeService;

    @Autowired
    public TimeServiceTest(TimeService timeService) {
        this.timeService = timeService;
    }

    @Test
    void getCurrentTimeTest() {
        var date = timeService.getCurrentTime();
        assertThat(date, equalTo(new Date()));
    }

    @Test
    void toTimestampTest() {
        var date = new Date(System.currentTimeMillis());
        var dateConverted = timeService.toTimestamp(date);
        var timestamp = new Timestamp(date.getTime());

        assertThat(dateConverted, equalTo(timestamp));
    }
}
