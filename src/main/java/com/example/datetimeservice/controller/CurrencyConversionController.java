package com.example.datetimeservice.controller;

import com.example.datetimeservice.model.CurrencyConversionRequest;
import com.example.datetimeservice.model.CurrencyConversionResponse;
import com.example.datetimeservice.service.CurrencyConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/currency")
public class CurrencyConversionController {

    private final CurrencyConversionService conversionService;

    public CurrencyConversionController(CurrencyConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @PostMapping("/convert")
    public CurrencyConversionResponse convert(@RequestBody CurrencyConversionRequest request) {
        var conversion = conversionService.convert(request.getFromCurrency(), request.getToCurrency(),
                request.getAmount());
        return CurrencyConversionResponse.builder()
                .fromCurrency(conversion.fromCurrency())
                .toCurrency(conversion.toCurrency())
                .amount(conversion.amount())
                .conversionRate(conversion.conversionRate())
                .convertedAmount(conversion.convertedAmount())
                .build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleIllegalArgumentException(IllegalArgumentException exception) {
        return Map.of("error", exception.getMessage());
    }
}

