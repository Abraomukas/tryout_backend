package com.example.demo;

import lombok.Getter;
import lombok.Setter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.simple.JSONObject;

import java.io.IOException;

@Getter
@Setter
public class Commission {

    private String amount;
    private Currency currency;

    public JSONObject getCommissionFrom(Transaction transaction) {
        JSONObject commission = new JSONObject();
        commission.put("amount", calculateCommissionFrom(transaction));
        commission.put("currency", Currency.EURO.label);

        return commission;
    }

    private String calculateCommissionFrom(Transaction transaction) {
        double commission = -1;
        // Rule #2
        if (transaction.getClient_id() == 42) {
            commission = 0.05;
        } else {
            // Rule #1
            commission = convertAmountViaAPI(transaction) * 0.005;
            if (commission < 0.05) {
                commission = 0.05;
            }
        }
        return String.format("%.2f", commission);
    }

    private double convertAmountViaAPI(Transaction transaction) {
        final String API_URL = "https://api.exchangerate.host/2021-01-01";
        double conversion = 1;
        double amount = Double.parseDouble(transaction.getAmount());
        String currency = transaction.getCurrency();

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(API_URL)
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("UNEXPECTED CODE: " + response);
            }
            
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return conversion * amount;
    }
}
