package com.designtartans.pigfarmingserver.services;

import com.designtartans.pigfarmingserver.dto.BodyResponse;
import com.designtartans.pigfarmingserver.dto.PigDto;
import com.designtartans.pigfarmingserver.model.Farm;
import com.designtartans.pigfarmingserver.model.Pig;
import com.designtartans.pigfarmingserver.model.PigStatus;
import com.designtartans.pigfarmingserver.repository.FarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.designtartans.pigfarmingserver.repository.PigRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PigService implements PigServiceInterface{

    @Autowired
    private PigRepository pigRepository;

    @Autowired
    private FarmRepository farmRepository;

    @Override
    public BodyResponse createPig(PigDto pigDto) {

        System.out.println("in pig service: ");

        if (!farmExists(pigDto.getFarmId())) {
            BodyResponse response = new BodyResponse();
            response.setStatusCode(HttpStatus.NOT_FOUND);
            response.setProcessed(false);
            response.setResult("Farm not found");
            return response;
        }
        Farm farm = farmRepository.findById(pigDto.getFarmId()).get();

        Pig pig = new Pig();
        pig.setLatestWeight(pigDto.getLatestWeight());
        pig.setGender(pigDto.getGender());
        pig.setBreed(pigDto.getBreed());
        pig.setParentId(pigDto.getParentId());
        pig.setDateAdded(pigDto.getDateAdded());
        pig.setPigStatus(PigStatus.valueOf(pigDto.getPigStatus()));
        pig.setFarm(farm);
        pigRepository.save(pig);

        BodyResponse response = new BodyResponse();
        response.setStatusCode(HttpStatus.CREATED);
        response.setProcessed(true);
        response.setResult(pig);
        return response;
    }

   //check if farm exists
    public boolean farmExists(long id) {
        return farmRepository.existsById(id);
    }

    // check if pig exists
    public boolean pigExists(long id) {
        return pigRepository.existsById(id);
    }
}
