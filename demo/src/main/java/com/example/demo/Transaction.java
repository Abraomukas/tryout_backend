package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class Transaction {
    private String date;
    private double amount;
    private Currency currency;
    private int client_id;
}
