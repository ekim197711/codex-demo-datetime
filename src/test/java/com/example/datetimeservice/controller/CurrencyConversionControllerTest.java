package com.example.datetimeservice.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.datetimeservice.model.CurrencyConversionRequest;
import com.example.datetimeservice.model.CurrencyConversionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class CurrencyConversionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void logHello() {
        System.out.println("hello " + new Random().nextInt());
    }

    @Test
    void convert_returnsConvertedPayload() throws Exception {
        var request = CurrencyConversionRequest.builder()
                .fromCurrency("USD")
                .toCurrency("GBP")
                .amount(new BigDecimal("75"))
                .build();

        var mvcResult = mockMvc.perform(post("/api/currency/convert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn();

        var response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), CurrencyConversionResponse.class);

        assertThat(response.getFromCurrency()).isEqualTo("USD");
        assertThat(response.getToCurrency()).isEqualTo("GBP");
        assertThat(response.getAmount()).isEqualByComparingTo("75");
        assertThat(response.getConvertedAmount()).isEqualByComparingTo("59.0551");
        assertThat(response.getConversionRate()).isEqualByComparingTo("0.7874");
    }
}

