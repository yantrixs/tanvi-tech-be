package com.tanvi.tech.tanvitechbe.controller;

import com.tanvi.tech.tanvitechbe.model.SellStock;
import com.tanvi.tech.tanvitechbe.security.service.JsonWebTokenService;
import com.tanvi.tech.tanvitechbe.security.service.SellService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class SellController {
    private static final Logger logger = Logger.getLogger(SellController.class);

    private final SellService sellService;
    private final JsonWebTokenService tokenService;

    public SellController(final JsonWebTokenService tokenService, final SellService sellService) {
        this.sellService = sellService;
        this.tokenService = tokenService;
    }

    @RequestMapping(value = "sells/add", method = RequestMethod.POST)
    public ResponseEntity<?> addSell(@RequestBody final SellStock sellStock,
                                     @RequestHeader final Map<String, String> headers) {
        Jws<Claims> claims = tokenService.tokenParser(headers.get("authorization"));
        if(claims != null) {
            Claims payLoad = claims.getBody();
            sellStock.setUserId(String.valueOf(payLoad.get("userID")));
            sellStock.setEmail(String.valueOf(payLoad.get("email")));
            sellStock.setUsername(String.valueOf(payLoad.get("username")));
            SellStock savedSellStocks = sellService.create(sellStock);
            logger.debug("Saved Stocks are ::: "+savedSellStocks);
            return new ResponseEntity<>(savedSellStocks, HttpStatus.OK);
        }
        else {
            // logger.debug(claims);
            return new ResponseEntity<>("something went wrong", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "sells/get", method = RequestMethod.GET)
    public ResponseEntity<?> getSellStockInfo(@RequestHeader final Map<String, String> headers) {
        Jws<Claims> claims = tokenService.tokenParser(headers.get("authorization"));
        if (claims != null) {
            List<SellStock> savedSellStocks = sellService.findAll();
            logger.debug("Getting list of stocks "+ savedSellStocks);
            return new ResponseEntity<>(savedSellStocks, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("something went wrong", HttpStatus.BAD_REQUEST);
        }
    }

}
