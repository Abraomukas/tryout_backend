package com.example.demo;

public enum Currency {
    EURO("EUR"),
    DOLLAR("USD");

    public final String label;

    Currency(String label) {
        this.label = label;
    }
}
