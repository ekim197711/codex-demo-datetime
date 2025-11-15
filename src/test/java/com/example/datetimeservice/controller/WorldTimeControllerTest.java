package com.example.datetimeservice.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import com.example.datetimeservice.model.CountryTimeResponse;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WorldTimeControllerTest {

    @Autowired
    private WorldTimeController controller;

    @Test
    void getWorldTimes_returnsFourWellFormedEntries() {
        var responses = controller.getWorldTimes();

        assertThat(responses)
                .as("Controller must list exactly four locations")
                .hasSize(4);

        responses.forEach(WorldTimeControllerTest::assertResponseShape);
        assertCountryCoverage(responses);
    }

    private static void assertResponseShape(CountryTimeResponse response) {
        assertThat(response.getCountry()).as("country must be populated").isNotBlank();
        assertThat(response.getCity()).as("city must be populated").isNotBlank();
        assertThat(response.getTimeZone()).as("timezone must be populated").isNotBlank();
        assertThat(response.getIsoDateTime()).as("isoDateTime must be populated").isNotBlank();
        assertValidIsoTimestamp(response.getIsoDateTime());
    }

    private static void assertValidIsoTimestamp(String isoTimestamp) {
        assertThatCode(() -> OffsetDateTime.parse(isoTimestamp))
                .as("isoDateTime must be ISO-8601 compliant but got %s", isoTimestamp)
                .doesNotThrowAnyException();
    }

    private static void assertCountryCoverage(List<CountryTimeResponse> responses) {
        var responseMap = responses.stream()
                .collect(Collectors.toMap(CountryTimeResponse::getCountry, response -> response, (left, right) -> left));

        assertThat(responseMap.keySet())
                .as("All four expected countries should be present")
                .containsExactlyInAnyOrder("United States", "United Kingdom", "Japan", "Australia");
    }
}
