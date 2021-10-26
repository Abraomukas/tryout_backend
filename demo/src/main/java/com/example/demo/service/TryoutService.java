package com.example.demo.service;

import com.example.demo.Commission;
import com.example.demo.Transaction;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class TryoutService {

    public JSONObject calculateCommission(JSONObject transactionJson) {
        String date = transactionJson.get("date").toString();
        String amount = transactionJson.get("amount").toString();
        String currencyJson = transactionJson.get("currency").toString();
        int client_id = (Integer) transactionJson.get("client_id");

        Transaction transaction = new Transaction(date, amount, currencyJson, client_id);
        Commission commission = new Commission();

        return commission.getCommissionFrom(transaction);
    }
}
