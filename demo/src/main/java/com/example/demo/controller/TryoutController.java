package com.example.demo.controller;

import com.example.demo.Transaction;
import com.example.demo.service.TryoutService;
import lombok.AllArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/backend")
public class TryoutController {

    private final TryoutService service;

    @GetMapping(path = "/")
    public String helloWorld() {
        return "Hello, World!";
    }

    @PostMapping(path = "/")
    public JSONObject getCommission(@RequestBody Transaction transactionJson) {
        return service.calculateCommission(transactionJson);
    }
}
