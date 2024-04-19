package com.designtartans.pigfarmingserver.controllers;

import com.designtartans.pigfarmingserver.dto.BodyResponse;
import com.designtartans.pigfarmingserver.dto.PigHealthRecordDto;
import com.designtartans.pigfarmingserver.exceptions.PigNotFoundException;
import com.designtartans.pigfarmingserver.services.PigHealthRecordServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pig-health-records")
@CrossOrigin(origins = "*")
public class PigHealthRecordController {
    @Autowired
    private PigHealthRecordServiceInterface pigHealthRecordService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('VET')")
    ResponseEntity<BodyResponse> createPigHealthRecord(@RequestBody PigHealthRecordDto pigHealthRecordDto) {
        return new ResponseEntity<>(pigHealthRecordService.createPigHealthRecord(pigHealthRecordDto),
                HttpStatus.CREATED);
    }

    @GetMapping("/pig/{tag}")
    @PreAuthorize("hasAuthority('FARMER')|| hasAuthority('VET')")
    ResponseEntity<BodyResponse> getPigHealthRecordsByTag(@PathVariable String tag) throws PigNotFoundException {
        return new ResponseEntity<>(pigHealthRecordService.getPigHealthRecordsByTag(tag), HttpStatus.OK);
    }

}
