package com.example.demo;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
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

    public static final double TRANSACTION_FEE = 0.005;
    private String amount;
    private Currency currency;

    public JSONObject getCommissionFrom(Transaction transaction) {
        JSONObject commission = new JSONObject();
        commission.put("amount", calculateCommissionFrom(transaction));
        commission.put("currency", Currency.EURO.label);

        return commission;
    }

    private String calculateCommissionFrom(Transaction transaction) {
        double commission = 0.00;
        double conversionRate = 1;

        if (!transaction.getCurrency().equals(Currency.EURO.label)) {
            conversionRate = getConversionRateFor(transaction);
        }

        commission = Double.parseDouble(transaction.getAmount()) * conversionRate * TRANSACTION_FEE;

        // Rule #1
        if (commission < 0.05) {
            commission = 0.05;
        }

        // Rule #2
        if (transaction.getClient_id() == 42) {
            commission = 0.05;
        }
        return String.format("%.2f", commission);
    }

    private double getConversionRateFor(Transaction transaction) {
        final String API_URL = "https://api.exchangerate.host/2021-01-01";
        double conversionRate = 1;
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
            JsonObject jsonObject = new Gson().fromJson(response.body().string(), JsonObject.class);
            JsonObject rates = new Gson().fromJson(jsonObject.get("rates").toString(), JsonObject.class);
            conversionRate = Double.parseDouble(rates.get(currency).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return conversionRate;
    }
}
