package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONObject;

@Getter
@Setter
public class Commission {

    private String amount;
    private Currency currency;

    public JSONObject getCommissionFrom(Transaction transaction) {
        JSONObject commission = new JSONObject();
        commission.put("amount", calculateCommission(transaction));
        commission.put("currency", Currency.EURO.label);

        return commission;
    }

    private double calculateCommission(Transaction transaction) {
        // Rule #2
        if (transaction.getClient_id() == 42) {
            return 0.05;
        } else {
            // Rule #1
            double commission = transaction.getAmount() * 0.05;
            return commission >= 0.05 ? commission:  0.05;
        }
    }
}
