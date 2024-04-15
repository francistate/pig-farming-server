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

        if (!farmExists(pigDto.getFarmId()) ) {
            BodyResponse response = new BodyResponse();
            response.setStatusCode(HttpStatus.NOT_FOUND);
            response.setProcessed(false);
            response.setResult("Farm not found");
            return response;
        }
        if (!pigExists(pigDto.getParentId())) {
            BodyResponse response = new BodyResponse();
            response.setStatusCode(HttpStatus.NOT_FOUND);
            response.setProcessed(false);
            response.setResult("Parent pig not found");
            return response;
        }

        Farm farm = farmRepository.findById(pigDto.getFarmId()).get();

        Pig pig = new Pig();
        pig.setLatestWeight(pigDto.getLatestWeight());
        pig.setGender(pigDto.getGender());
        pig.setBreed(pigDto.getBreed());
        pig.setParentId(pigDto.getParentId());
        pig.setDateOfBirth(pigDto.getDateOfBirth());
        pig.setPigStatus(PigStatus.valueOf(pigDto.getPigStatus()));
        pig.setFarm(farm);
        pigRepository.save(pig);

        BodyResponse response = new BodyResponse();
        response.setStatusCode(HttpStatus.CREATED);
        response.setProcessed(true);
        response.setResult(pig);
        return response;
    }

    public BodyResponse updatePigStatus(Long id, PigDto pigDto){
        if (!pigExists(id)) {
            BodyResponse response = new BodyResponse();
            response.setStatusCode(HttpStatus.NOT_FOUND);
            response.setProcessed(false);
            response.setResult("Pig not found");
            return response;
        }
        Pig pig = pigRepository.findById(id).get();
        if (pigDto.getPigStatus() != null && (pig.getPigStatus() != PigStatus.valueOf(pigDto.getPigStatus()))) {
            pig.setPigStatus(PigStatus.valueOf(pigDto.getPigStatus()));
        }
        pigRepository.save(pig);

        BodyResponse response = new BodyResponse();
        response.setStatusCode(HttpStatus.OK);
        response.setProcessed(true);
        response.setResult(pig);

        return response;
    }

   //check if farm exists
    private boolean farmExists(long id) {
        return farmRepository.existsById(id);
    }

    // check if pig exists
    private boolean pigExists(long id) {
        return pigRepository.existsById(id);
    }
}
