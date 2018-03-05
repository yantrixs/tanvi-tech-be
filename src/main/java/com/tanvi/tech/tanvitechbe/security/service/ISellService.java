package com.tanvi.tech.tanvitechbe.security.service;

import com.tanvi.tech.tanvitechbe.model.SellStock;

import java.util.List;

public interface ISellService {
    List<SellStock> findAll();

    SellStock update(String id, SellStock stock);

    String delete(String id);

    SellStock create(SellStock sellStock);

    SellStock find(String id);
}
