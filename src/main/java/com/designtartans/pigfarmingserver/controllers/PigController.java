package com.designtartans.pigfarmingserver.controllers;

import com.designtartans.pigfarmingserver.dto.BodyResponse;
import com.designtartans.pigfarmingserver.dto.PigDto;
import com.designtartans.pigfarmingserver.exceptions.FarmNotFoundException;
import com.designtartans.pigfarmingserver.exceptions.InvalidArgumentException;
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
    @PreAuthorize("hasAuthority('MINISTER')") // for minister
    public ResponseEntity<BodyResponse> countAllActivePigs() {
        return new ResponseEntity<>(pigService.countAllActivePigs(), HttpStatus.OK);
    }

    @GetMapping("/count/active_by_region")
    @PreAuthorize("hasAuthority('MINISTER')") // for minister
    public ResponseEntity<BodyResponse> countActivePigsPerRegion() {
        return new ResponseEntity<>(pigService.countActivePigsPerProvince(), HttpStatus.OK);
    }

    @GetMapping("/pig")
    @PreAuthorize("hasAuthority('FARMER') || hasAuthority('VET')")
    public ResponseEntity<BodyResponse> getPigByTag(@RequestParam String tag) throws PigNotFoundException {
        return new ResponseEntity<>(pigService.getPigByTag(tag), HttpStatus.OK);
    }

    @GetMapping("/gender-count/farm")
    @PreAuthorize("hasAuthority('FARMER')")
    public ResponseEntity<BodyResponse> getPigGenderCountByFarm(@RequestParam Long id) throws FarmNotFoundException {
        return new ResponseEntity<>(pigService.getPigGenderCountForFarm(id), HttpStatus.OK);
    }

    @GetMapping("/breed-count/farm")
    @PreAuthorize("hasAuthority('FARMER') || hasAuthority('MINISTER')")
    public ResponseEntity<BodyResponse> getBreedCountForFarm(@RequestParam Long id) throws FarmNotFoundException {
        return new ResponseEntity<>(pigService.getBreedCountForFarm(id), HttpStatus.OK);
    }

    @GetMapping("/breed-count/all")
    @PreAuthorize("hasAuthority('MINISTER')")
    public ResponseEntity<BodyResponse> getBreedCountForAllFarmsCombined() {
        return new ResponseEntity<>(pigService.getBreedCountForAllFarmsCombined(), HttpStatus.OK);
    }

    @GetMapping("/location")
    @PreAuthorize("hasAuthority('MINISTER')")
    public ResponseEntity<BodyResponse> getPigsCountByLocation(@RequestParam(required = false) String province,
            @RequestParam(required = false) String district) throws InvalidArgumentException {
        if (province != null) {
            return new ResponseEntity<>(pigService.getPigsByProvince(province), HttpStatus.OK);
        } else if (district != null) {
            return new ResponseEntity<>(pigService.getPigsByDistrict(district), HttpStatus.OK);
        } else {
            throw new InvalidArgumentException("Invalid arguments passed");
        }
    }
}
