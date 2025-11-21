package com.example.datetimeservice.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 * Performs simple currency conversion using static USD-based rates.
 */
@Service
public class CurrencyConversionService {

    private static final Map<String, BigDecimal> USD_RATES = Map.of(
            "USD", new BigDecimal("1.00"),
            "EUR", new BigDecimal("1.08"),
            "GBP", new BigDecimal("1.27"),
            "JPY", new BigDecimal("0.0071"),
            "AUD", new BigDecimal("0.66"),
            "CAD", new BigDecimal("0.74"));
    private static final int SCALE = 4;

    public CurrencyConversion convert(String fromCurrency, String toCurrency, BigDecimal amount) {
        if (amount == null) {
            throw new IllegalArgumentException("amount must be provided");
        }
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        }

        var normalizedFrom = normalizeCurrency(fromCurrency);
        var normalizedTo = normalizeCurrency(toCurrency);
        var fromRate = rateFor(normalizedFrom);
        var toRate = rateFor(normalizedTo);

        var conversionRate = fromRate.divide(toRate, SCALE, RoundingMode.HALF_UP);
        var convertedAmount = amount.multiply(fromRate).divide(toRate, SCALE, RoundingMode.HALF_UP);

        return new CurrencyConversion(normalizedFrom, normalizedTo, amount, conversionRate, convertedAmount);
    }

    private static String normalizeCurrency(String currency) {
        if (currency == null || currency.isBlank()) {
            throw new IllegalArgumentException("currency code must be provided");
        }
        return currency.trim().toUpperCase(Locale.ROOT);
    }

    private static BigDecimal rateFor(String currency) {
        var rate = USD_RATES.get(currency);
        if (rate == null) {
            throw new IllegalArgumentException("Unsupported currency: " + currency);
        }
        return rate;
    }

    public record CurrencyConversion(
            String fromCurrency, String toCurrency, BigDecimal amount, BigDecimal conversionRate, BigDecimal convertedAmount) {}
}
