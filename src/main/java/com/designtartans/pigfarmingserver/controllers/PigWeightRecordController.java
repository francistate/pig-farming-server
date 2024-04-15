package com.designtartans.pigfarmingserver.controllers;


import com.designtartans.pigfarmingserver.dto.BodyResponse;

import com.designtartans.pigfarmingserver.dto.PigWeightRecordDto;
import com.designtartans.pigfarmingserver.services.PigWeightRecordService;
import com.designtartans.pigfarmingserver.services.PigWeightRecordServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pig-weight-records")
@CrossOrigin(origins = "*")
public class PigWeightRecordController {

    @Autowired
    private PigWeightRecordServiceInterface pigWeightRecordService;

    public PigWeightRecordController(PigWeightRecordService pigWeightRecordService) {
        this.pigWeightRecordService = pigWeightRecordService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('VET')")
    ResponseEntity<BodyResponse> createPigHealthRecord(@RequestBody PigWeightRecordDto pigWeightRecordDto) {
        return new ResponseEntity<>(pigWeightRecordService.createPigWeightRecord(pigWeightRecordDto), HttpStatus.CREATED);
    }

}
