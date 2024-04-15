package com.designtartans.pigfarmingserver.services;

import com.designtartans.pigfarmingserver.dto.BodyResponse;
import com.designtartans.pigfarmingserver.dto.PigWeightRecordDto;
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


    public BodyResponse createPigWeightRecord(PigWeightRecordDto pigWeightRecordDto) {
        if (!pigExists(pigWeightRecordDto.getPigId())) {
            BodyResponse response = new BodyResponse();
            HttpStatus status = HttpStatus.NOT_FOUND;
            response.setProcessed(false);
            response.setResult("Pig not found");
            return response;
        }

        Pig pig = pigRepository.findById(pigWeightRecordDto.getPigId()).get();
        PigWeightRecord pigWeightRecord = new PigWeightRecord();
        pigWeightRecord.setPig(pig);
        pigWeightRecord.setWeight(pigWeightRecordDto.getWeight());
        pigWeightRecordRepository.save(pigWeightRecord);

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
