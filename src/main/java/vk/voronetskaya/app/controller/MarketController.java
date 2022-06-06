package vk.voronetskaya.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import vk.voronetskaya.app.service.MarketService;
import vk.voronetskaya.app.model.Market;

import java.util.Map;

@RestController
@RequestMapping(value = "/market")
public class MarketController {
    @Autowired
    private MarketService service;

    @PostMapping(value = "/create", consumes = {"application/json"})
    public ResponseEntity<?> createMarket(@RequestBody Market market) {
        service.createMarket(market);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getMarketInfo() {
        Market market = service.getMarket();
        return new ResponseEntity<>(market, HttpStatus.OK);
    }

    @PostMapping(value = "/deal")
    public ResponseEntity<?> makeDeal(@RequestBody Map<String, Integer> data) {
        try {
            service.makeDeal(data.get("account_id"), data.get("product_id"), data.get("amount"));
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
