package com.example.demo.controller;

import com.example.demo.service.TryoutService;
import lombok.AllArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/backend")
public class TryoutController {

    private final TryoutService service;

    @PostMapping(path = "/")
    public JSONObject getCommission(@RequestBody JSONObject transactionJson) {
        return service.calculateCommission(transactionJson);
    }
}
