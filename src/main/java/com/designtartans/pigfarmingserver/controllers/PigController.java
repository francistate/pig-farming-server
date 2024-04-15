package com.designtartans.pigfarmingserver.controllers;

import com.designtartans.pigfarmingserver.dto.BodyResponse;
import com.designtartans.pigfarmingserver.dto.PigDto;
import com.designtartans.pigfarmingserver.services.PigService;
import com.designtartans.pigfarmingserver.services.PigServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pigs")
@CrossOrigin(origins = "*")
public class PigController {

    @Autowired
    private PigServiceInterface pigService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('FARMER')")
    public ResponseEntity<BodyResponse> createPig(@RequestBody PigDto pigDto) {
        System.out.println("PigDto: " + pigDto);
        return new ResponseEntity<>(pigService.createPig(pigDto), HttpStatus.CREATED);
    }
}
