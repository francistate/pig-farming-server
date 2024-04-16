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

    @Override
    public BodyResponse getPigsByFarm(Long farmId) {
        if (!farmExists(farmId)) {
            BodyResponse response = new BodyResponse();
            response.setStatusCode(HttpStatus.NOT_FOUND);
            response.setProcessed(false);
            response.setResult("Farm not found");
            return response;
        }


        BodyResponse response = new BodyResponse();
        response.setStatusCode(HttpStatus.OK);
        response.setProcessed(true);
        response.setResult(pigRepository.findByFarmId(farmId));
        return response;
    }


    public BodyResponse updatePig(long pigId, PigDto pigDto) {
        // Check if the pig exists
        if (!pigExists(pigId)) {
            BodyResponse response = new BodyResponse();
            response.setStatusCode(HttpStatus.NOT_FOUND);
            response.setProcessed(false);
            response.setResult("Pig not found");
            return response;
        }

        // Retrieve the existing pig from the database
        Pig existingPig = pigRepository.findById(pigId).orElse(null);
        if (existingPig == null) {
            BodyResponse response = new BodyResponse();
            response.setStatusCode(HttpStatus.NOT_FOUND);
            response.setProcessed(false);
            response.setResult("Pig not found");
            return response;
        }

        // Update the pig's properties with the non-null values from the DTO
        if (pigDto.getLatestWeight() != null) {
            existingPig.setLatestWeight(pigDto.getLatestWeight());
        }
        if (pigDto.getGender() != null) {
            existingPig.setGender(pigDto.getGender());
        }
        if (pigDto.getBreed() != null) {
            existingPig.setBreed(pigDto.getBreed());
        }
        if (pigDto.getDateOfBirth() != null) {
            existingPig.setDateOfBirth(pigDto.getDateOfBirth());
        }
        if (pigDto.getPigStatus() != null) {
            existingPig.setPigStatus(PigStatus.valueOf(pigDto.getPigStatus()));
        }

        // Save the updated pig to the database
        pigRepository.save(existingPig);

        // Prepare and return the response
        BodyResponse response = new BodyResponse();
        response.setStatusCode(HttpStatus.OK);
        response.setProcessed(true);
        response.setResult(existingPig);
        return response;
    }

    BodyResponse getPigById(long pigId) {
        // Check if the pig exists
//        if (!pigExists(pigId)) {
//            BodyResponse response = new BodyResponse();
//            response.setStatusCode(HttpStatus.NOT_FOUND);
//            response.setProcessed(false);
//            response.setResult("Pig not found");
//            return response;
//        }

        // Retrieve the pig from the database
        Pig pig = pigRepository.findById(pigId).orElse(null);
        if (pig == null) {
            BodyResponse response = new BodyResponse();
            response.setStatusCode(HttpStatus.NOT_FOUND);
            response.setProcessed(false);
            response.setResult("Pig not found");
            return response;
        }

        // Prepare and return the response
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
