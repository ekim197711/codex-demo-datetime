package com.example.datetimeservice.model;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * Immutable DTO describing the current time for a specific country/city pair.
 */
@Value
@Builder
@Jacksonized
public class CountryTimeResponse {
    String country;
    String city;
    String timeZone;
    String isoDateTime;
}

