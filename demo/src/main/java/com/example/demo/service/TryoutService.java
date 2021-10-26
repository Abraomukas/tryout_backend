package com.example.demo.service;

import com.example.demo.Commission;
import com.example.demo.Currency;
import com.example.demo.Transaction;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class TryoutService {

    public JSONObject calculateCommission(JSONObject transactionJson) {
        String date = transactionJson.get("date").toString();
        double amount = (double) transactionJson.get("amount");
        Currency currency = (Currency) transactionJson.get("currenty");
        int client_id = (Integer) transactionJson.get("client_id");

        Transaction transaction = new Transaction(date, amount, currency, client_id);
        Commission commission = new Commission();

        return commission.getCommissionFrom(transaction);
    }
}
