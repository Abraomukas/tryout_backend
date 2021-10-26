package com.example.demo.service;

import com.example.demo.Commission;
import com.example.demo.Transaction;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class TryoutService {

    public JSONObject calculateCommission(Transaction transaction) {
        Commission commission = new Commission();

        return commission.getCommissionFrom(transaction);
    }
}
