package com.example.datetimeservice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CurrencyConversionServiceTest {

    private CurrencyConversionService service;

    @BeforeEach
    void setUp() {
        System.out.println("hello " + new Random().nextInt());
        service = new CurrencyConversionService();
    }

    @Test
    void convert_usesStaticRatesToComputeAmount() {
        var result = service.convert("usd", "eur", new BigDecimal("50"));

        assertThat(result.convertedAmount()).isEqualByComparingTo("46.2963");
        assertThat(result.conversionRate()).isEqualByComparingTo("0.9259");
        assertThat(result.fromCurrency()).isEqualTo("USD");
        assertThat(result.toCurrency()).isEqualTo("EUR");
    }

    @Test
    void convert_rejectsUnknownCurrency() {
        assertThatThrownBy(() -> service.convert("USD", "ZZZ", BigDecimal.ONE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Unsupported currency");
    }
}

