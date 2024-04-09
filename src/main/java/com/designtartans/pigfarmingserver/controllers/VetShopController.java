package com.designtartans.pigfarmingserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.designtartans.pigfarmingserver.dto.BodyResponse;
import com.designtartans.pigfarmingserver.dto.VetShopDto;
import com.designtartans.pigfarmingserver.exceptions.ExistingVetShopNameException;
import com.designtartans.pigfarmingserver.services.VetShopServiceInterface;

@RestController
@RequestMapping("api/v1/shop/")
@CrossOrigin(origins = "*")
public class VetShopController {

    @Autowired
    private VetShopServiceInterface vetShopServiceInterface;

    @PostMapping("create")
    public ResponseEntity<BodyResponse> createVetShop(@RequestBody VetShopDto vetShopDto)
            throws ExistingVetShopNameException {
        return new ResponseEntity<>(vetShopServiceInterface.createVetShop(vetShopDto), HttpStatus.CREATED);
    }
}
