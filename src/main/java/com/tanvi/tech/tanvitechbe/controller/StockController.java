package com.tanvi.tech.tanvitechbe.controller;

import com.tanvi.tech.tanvitechbe.model.Stock;
import com.tanvi.tech.tanvitechbe.model.User;
import com.tanvi.tech.tanvitechbe.security.service.StockService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.TextCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class StockController {
    private static final Logger logger = Logger.getLogger(StockController.class);

    @Value("security.token.secret.key")
    private String tokenKey;

    private final StockService stockService;

    @Autowired
    public StockController(final StockService stockService) {
        this.stockService = stockService;
    }

    @RequestMapping(value = "add/stock", method = RequestMethod.POST)
    public ResponseEntity<?> addStock(@RequestBody final List<Stock> stocks,
                                      @RequestHeader final Map<String, String> headers) {
        String token = headers.get("authorization");
        Jws<Claims> claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(tokenKey)
                    .parseClaimsJws(token);
            logger.debug("debug claims is  " + claims);
        } catch (MissingClaimException | IncorrectClaimException e) {
            logger.debug(e.getMessage());
        }

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
}