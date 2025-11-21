package com.example.datetimeservice.model;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class CurrencyConversionResponse {
    String fromCurrency;
    String toCurrency;
    BigDecimal amount;
    BigDecimal conversionRate;
    BigDecimal convertedAmount;
}

