package com.designtartans.pigfarmingserver.controllers;

import com.designtartans.pigfarmingserver.dto.BodyResponse;
import com.designtartans.pigfarmingserver.dto.PigDto;
import com.designtartans.pigfarmingserver.exceptions.FarmNotFoundException;
import com.designtartans.pigfarmingserver.exceptions.PigNotFoundException;
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
    public ResponseEntity<BodyResponse> createPig(@RequestBody PigDto pigDto)
            throws PigNotFoundException, FarmNotFoundException {
        return new ResponseEntity<>(pigService.createPig(pigDto), HttpStatus.CREATED);
    }

    @GetMapping("/farm/{farmId}")
    @PreAuthorize("hasAuthority('FARMER')")
    public ResponseEntity<BodyResponse> getPigsByFarm(@PathVariable Long farmId) throws FarmNotFoundException {
        return new ResponseEntity<>(pigService.getPigsByFarm(farmId), HttpStatus.OK);
    }

    @GetMapping("/count/active")
    @PreAuthorize("hasAuthority('FARMER')") // for minister
    public ResponseEntity<BodyResponse> countAllActivePigs() {
        return new ResponseEntity<>(pigService.countAllActivePigs(), HttpStatus.OK);
    }

    @GetMapping("/count/active_by_region")
    @PreAuthorize("hasAuthority('FARMER')") // for minister
    public ResponseEntity<BodyResponse> countActivePigsPerRegion() {
        return new ResponseEntity<>(pigService.countActivePigsPerProvince(), HttpStatus.OK);
    }

}
