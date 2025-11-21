package com.example.datetimeservice.model;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class CurrencyConversionRequest {
    String fromCurrency;
    String toCurrency;
    BigDecimal amount;
}

