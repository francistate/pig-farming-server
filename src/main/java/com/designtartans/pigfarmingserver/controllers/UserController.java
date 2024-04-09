package com.designtartans.pigfarmingserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.designtartans.pigfarmingserver.dto.AuthenticationRequest;
import com.designtartans.pigfarmingserver.dto.BodyResponse;
import com.designtartans.pigfarmingserver.dto.FarmerDto;
import com.designtartans.pigfarmingserver.dto.VetDto;
import com.designtartans.pigfarmingserver.exceptions.InvalidShopIdException;
import com.designtartans.pigfarmingserver.exceptions.PhoneNumberAlreadyExistException;
import com.designtartans.pigfarmingserver.services.FarmerServiceInterface;
import com.designtartans.pigfarmingserver.services.UserService;
import com.designtartans.pigfarmingserver.services.VetServiceInterface;

@RestController
@RequestMapping("/api/v1/auth/")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private FarmerServiceInterface farmerService;

    @Autowired
    private UserService userService;

    @Autowired
    private VetServiceInterface vetServiceInterface;

    @PostMapping("farmer/signup")
    public ResponseEntity<BodyResponse> createFarmer(@RequestBody FarmerDto farmerDto)
            throws PhoneNumberAlreadyExistException {
        return new ResponseEntity<>(farmerService.createFarmer(farmerDto), HttpStatus.CREATED);
    }

    @PostMapping("vet/signup")
    public ResponseEntity<BodyResponse> createVet(@RequestBody VetDto vetDto)
            throws PhoneNumberAlreadyExistException, InvalidShopIdException {
        return new ResponseEntity<>(vetServiceInterface.createVet(vetDto), HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity<BodyResponse> login(@RequestBody AuthenticationRequest request) {
        return new ResponseEntity<>(userService.authenticate(request), HttpStatus.OK);
    }
}
