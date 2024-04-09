package com.designtartans.pigfarmingserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.designtartans.pigfarmingserver.dto.BodyResponse;
import com.designtartans.pigfarmingserver.dto.FarmDto;
import com.designtartans.pigfarmingserver.model.Farm;
import com.designtartans.pigfarmingserver.model.Farmer;
import com.designtartans.pigfarmingserver.repository.FarmRepository;
import com.designtartans.pigfarmingserver.repository.FarmerRepository;

@Service
public class FarmService implements FarmServiceInterface {

    @Autowired
    private FarmRepository farmRepository;

    @Autowired
    private FarmerRepository farmerRepository;

    @Override
    public BodyResponse createFarm(FarmDto farmDto) {
        Farm farm = new Farm();
        farm.setDistrict(farmDto.getDistrict());
        farm.setProvince(farmDto.getProvince());
        farmRepository.save(farm);

        Farmer farmer = farmerRepository.findById(farmDto.getFarmerId()).get();
        farmer.setFarm(farm);
        BodyResponse response = new BodyResponse();
        response.setStatusCode(HttpStatus.CREATED);
        response.setProcessed(true);
        response.setResult(farm);
        return response;
    }

    @Override
    public Farm getFarm(long id) {
        return farmRepository.findById(id).get();
    }

}
