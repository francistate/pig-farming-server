package com.designtartans.pigfarmingserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.designtartans.pigfarmingserver.dto.FarmDto;
import com.designtartans.pigfarmingserver.model.Farm;
import com.designtartans.pigfarmingserver.repository.FarmRepository;

@Service
public class FarmService implements FarmServiceInterface {

    @Autowired
    private FarmRepository farmRepository;

    @Override
    public Farm createFarm(FarmDto farmDto) {
        Farm farm = new Farm();
        farm.setDistrict(farmDto.getDistrict());
        farm.setProvince(farmDto.getProvince());
        farmRepository.save(farm);
        return farm;
    }

    @Override
    public Farm getFarm(long id) {
        return farmRepository.findById(id).get();
    }

}
