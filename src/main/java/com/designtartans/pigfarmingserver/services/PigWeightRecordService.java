package com.designtartans.pigfarmingserver.services;

import com.designtartans.pigfarmingserver.dto.BodyResponse;
import com.designtartans.pigfarmingserver.dto.PigWeightRecordDto;
import com.designtartans.pigfarmingserver.exceptions.PigNotFoundException;
import com.designtartans.pigfarmingserver.model.Pig;
import com.designtartans.pigfarmingserver.model.PigWeightRecord;
import com.designtartans.pigfarmingserver.repository.PigRepository;
import com.designtartans.pigfarmingserver.repository.PigWeightRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PigWeightRecordService implements PigWeightRecordServiceInterface {

    @Autowired
    private PigWeightRecordRepository pigWeightRecordRepository;

    @Autowired
    private PigRepository pigRepository;

    public BodyResponse createPigWeightRecord(PigWeightRecordDto pigWeightRecordDto) throws PigNotFoundException {
        if (!pigExists(pigWeightRecordDto.getPigId())) {
            throw new PigNotFoundException("Pig Not found");
        }

        Pig pig = pigRepository.findById(pigWeightRecordDto.getPigId()).get();
        PigWeightRecord pigWeightRecord = new PigWeightRecord();
        pigWeightRecord.setPig(pig);
        pigWeightRecord.setWeight(pigWeightRecordDto.getWeight());
        pigWeightRecordRepository.save(pigWeightRecord);

        // Update the latest weight of the pig
        pig.setLatestWeight(pigWeightRecordDto.getWeight());
        pigRepository.save(pig);

        BodyResponse response = new BodyResponse();
        response.setStatusCode(HttpStatus.CREATED);
        response.setProcessed(true);
        response.setResult(pigWeightRecord);

        return response;
    }

    // check if pig exists
    private boolean pigExists(long id) {
        return pigRepository.existsById(id);
    }

}
