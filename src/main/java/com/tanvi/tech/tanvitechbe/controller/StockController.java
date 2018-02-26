package com.tanvi.tech.tanvitechbe.controller;

import com.tanvi.tech.tanvitechbe.model.Stock;
import com.tanvi.tech.tanvitechbe.security.service.JsonWebTokenService;
import com.tanvi.tech.tanvitechbe.security.service.StockService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class StockController {
    private static final Logger logger = Logger.getLogger(StockController.class);

    private final StockService stockService;

    private final JsonWebTokenService tokenService;

    @Autowired
    public StockController(final StockService stockService, final JsonWebTokenService tokenService) {
        this.stockService = stockService;
        this.tokenService = tokenService;
    }

    @RequestMapping(value = "stocks/add", method = RequestMethod.POST)
    public ResponseEntity<?> addStock(@RequestBody final List<Stock> stocks,
                                      @RequestHeader final Map<String, String> headers) {
        Jws<Claims> claims = tokenService.tokenParser(headers.get("authorization"));
        if (claims != null) {
            Claims payLoad = claims.getBody();
            for (Stock stock : stocks) {
                stock.setUserId(String.valueOf(payLoad.get("userID")));
                stock.setEmail(String.valueOf(payLoad.get("email")));
                stock.setUsername(String.valueOf(payLoad.get("username")));
            }
            List<Stock> savedStock = stockService.create(stocks);
            return new ResponseEntity<>(savedStock, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("something went wrong", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "stocks/get", method = RequestMethod.GET)
    public ResponseEntity<?> getStockInfo(@RequestHeader final Map<String, String> headers) {
        Jws<Claims> claims = tokenService.tokenParser(headers.get("authorization"));
        if (claims != null) {
            List<Stock> savedStocks = stockService.findAll();
            logger.debug("Getting list of stocks "+ savedStocks);
            return new ResponseEntity<>(savedStocks, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("something went wrong", HttpStatus.BAD_REQUEST);
        }
    }
}