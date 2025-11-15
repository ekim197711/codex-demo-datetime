package com.example.datetimeservice.controller;

import com.example.datetimeservice.model.CountryTimeResponse;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/time")
public class WorldTimeController {

    private static final DateTimeFormatter ISO_FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
    private static final List<Location> LOCATIONS = List.of(
            new Location("United States", "New York", ZoneId.of("America/New_York")),
            new Location("United Kingdom", "London", ZoneId.of("Europe/London")),
            new Location("Japan", "Tokyo", ZoneId.of("Asia/Tokyo")),
            new Location("Australia", "Sydney", ZoneId.of("Australia/Sydney")));

    @GetMapping
    public List<CountryTimeResponse> getWorldTimes() {
        return LOCATIONS.stream()
                .map(location -> CountryTimeResponse.builder()
                        .country(location.country())
                        .city(location.city())
                        .timeZone(location.zoneId().getId())
                        .isoDateTime(currentTime(location.zoneId()))
                        .build())
                .toList();
    }

    private static String currentTime(ZoneId zoneId) {
        return ZonedDateTime.now(zoneId).format(ISO_FORMATTER);
    }

    private record Location(String country, String city, ZoneId zoneId) {}
}
