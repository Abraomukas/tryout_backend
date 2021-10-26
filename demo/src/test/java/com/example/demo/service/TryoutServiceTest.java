package com.example.demo.service;

import com.example.demo.Currency;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TryoutServiceTest {

    private TryoutService underTest;

    @BeforeEach
    void setUp() {
        underTest = new TryoutService();
    }

    @Test
    void calculateCommission_ruleOne() {
        // given
        String date = "2021-10-26";
        double amount = 500.0;
        Currency currency = Currency.EURO;
        int client_id = 1;

        JSONObject transaction = new JSONObject();
        transaction.put("date", date);
        transaction.put("amount", amount);
        transaction.put("currency", currency);
        transaction.put("client_id", client_id);

        JSONObject expected = new JSONObject();
        expected.put("amount", "2.5");
        expected.put("currency", "EUR");

        // when
        JSONObject result = underTest.calculateCommission(transaction);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void calculateCommission_ruleTwo() {
        // given
        String date = "2021-10-26";
        double amount = 2000.0;
        Currency currency = Currency.EURO;
        int client_id = 42;
        JSONObject transaction = new JSONObject();
        transaction.put("date", date);
        transaction.put("amount", amount);
        transaction.put("currency", currency);
        transaction.put("client_id", client_id);

        JSONObject expected = new JSONObject();
        expected.put("amount", "0.05");
        expected.put("currency", "EUR");

        // when
        JSONObject result = underTest.calculateCommission(transaction);

        // then
        assertThat(result).isEqualTo(expected);
    }
}