package com.example.demo;

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

    private String calculateCommission(Transaction transaction) {
        double commission = -1;
        // Rule #2
        if (transaction.getClient_id() == 42) {
            commission = 0.05;
        } else {
            // Rule #1
            commission = Double.parseDouble(transaction.getAmount()) * 0.005;
            if (commission < 0.05) {
                commission = 0.05;
            }
        }
        return String.valueOf(commission);
    }
}
