package com.example.datetimeservice.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import com.example.datetimeservice.model.CountryTimeResponse;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WorldTimeControllerSpecificValuesTest {

    @Autowired
    private WorldTimeController controller;

    private List<CountryTimeResponse> responses;

    @BeforeEach
    void setUp() {
        System.out.println("hello " + new Random().nextInt());
        responses = controller.getWorldTimes();
    }

    @Test
    void getWorldTimes_containsAllExpectedLocationMetadata() {
        assertThat(responses)
                .hasSize(4)
                .extracting(CountryTimeResponse::getCountry,
                        CountryTimeResponse::getCity,
                        CountryTimeResponse::getTimeZone)
                .containsExactlyInAnyOrder(
                        tuple("United States", "New York", "America/New_York"),
                        tuple("United Kingdom", "London", "Europe/London"),
                        tuple("Japan", "Tokyo", "Asia/Tokyo"),
                        tuple("Australia", "Sydney", "Australia/Sydney"));
    }
}
