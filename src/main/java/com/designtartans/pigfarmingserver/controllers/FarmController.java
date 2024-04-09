package com.designtartans.pigfarmingserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.designtartans.pigfarmingserver.dto.BodyResponse;
import com.designtartans.pigfarmingserver.dto.FarmDto;
import com.designtartans.pigfarmingserver.services.FarmService;

@RestController
@RequestMapping("api/v1/farm")
@CrossOrigin(origins = "*")
public class FarmController {

    @Autowired
    private FarmService farmService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('FARMER')")
    public ResponseEntity<BodyResponse> createFarm(@RequestBody FarmDto farmDto) {
        return new ResponseEntity<>(farmService.createFarm(farmDto), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('VET')")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("Hello there", HttpStatus.OK);
    }
}
