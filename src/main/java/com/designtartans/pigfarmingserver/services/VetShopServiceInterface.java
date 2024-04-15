package com.designtartans.pigfarmingserver.services;

import com.designtartans.pigfarmingserver.dto.BodyResponse;
import com.designtartans.pigfarmingserver.dto.VetShopDto;
import com.designtartans.pigfarmingserver.exceptions.ExistingVetShopNameException;
import com.designtartans.pigfarmingserver.model.VetShop;

import java.util.ArrayList;

public interface VetShopServiceInterface {
    BodyResponse createVetShop(VetShopDto shopDto) throws ExistingVetShopNameException;

    VetShop findShopById(long id);

    BodyResponse findAllShops();
}
