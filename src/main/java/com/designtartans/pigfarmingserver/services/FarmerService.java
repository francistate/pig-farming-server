package com.designtartans.pigfarmingserver.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.designtartans.pigfarmingserver.dto.BodyResponse;
import com.designtartans.pigfarmingserver.dto.FarmDto;
import com.designtartans.pigfarmingserver.dto.FarmerDto;
import com.designtartans.pigfarmingserver.dto.UserDto;
import com.designtartans.pigfarmingserver.exceptions.PhoneNumberAlreadyExistException;
import com.designtartans.pigfarmingserver.model.Farm;
import com.designtartans.pigfarmingserver.model.Farmer;
import com.designtartans.pigfarmingserver.model.User;
import com.designtartans.pigfarmingserver.repository.FarmerRepository;
import com.designtartans.pigfarmingserver.utils.JwtService;

@Service
public class FarmerService implements FarmerServiceInterface {

    @Autowired
    private UserService userService;

    @Autowired
    private FarmerRepository farmerRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private FarmService farmService;

    @Override
    public BodyResponse createFarmer(FarmerDto farmerDto) throws PhoneNumberAlreadyExistException {

        User user = userService.createUser(
                new UserDto(farmerDto.getFirstname(), farmerDto.getLastname(), farmerDto.getPhoneNumber(),
                        farmerDto.getPassword(), "FARMER"));

        Farm farm = farmService.createFarm(new FarmDto(farmerDto.getProvince(), farmerDto.getDistrict()));

        Farmer farmer = new Farmer();
        farmer.setUser(user);
        farmer.setFarm(farm);

        farmerRepository.save(farmer);

        String jwt = jwtService.generateToken(user);

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("farmerID", farmer.getId());
        responseBody.put("userID", user.getUserId());
        responseBody.put("token", jwt);
        responseBody.put("firstname", user.getFirstName());
        responseBody.put("lastname", user.getLastName());
        responseBody.put("phoneNumber", user.getPhoneNumber());
        responseBody.put("role", user.getRole());
        responseBody.put("farmID", farmer.getFarm().getId());

        BodyResponse response = new BodyResponse();
        response.setStatusCode(HttpStatus.CREATED);
        response.setProcessed(true);
        response.setResult(responseBody);
        return response;

    }

}
