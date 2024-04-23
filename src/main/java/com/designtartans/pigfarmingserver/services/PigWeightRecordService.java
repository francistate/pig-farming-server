package com.designtartans.pigfarmingserver.services;

import com.designtartans.pigfarmingserver.dto.BodyResponse;
import com.designtartans.pigfarmingserver.dto.PigWeightRecordDto;
import com.designtartans.pigfarmingserver.exceptions.PigNotFoundException;
import com.designtartans.pigfarmingserver.exceptions.TagNotFoundException;
import com.designtartans.pigfarmingserver.model.Pig;
import com.designtartans.pigfarmingserver.model.PigWeightRecord;
import com.designtartans.pigfarmingserver.model.WeightResponseData;
import com.designtartans.pigfarmingserver.repository.PigRepository;
import com.designtartans.pigfarmingserver.repository.PigWeightRecordRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public BodyResponse getPigWeightRecords(String tag) throws TagNotFoundException {
        Optional<Pig> pig = pigRepository.findByTag(tag);
        if (pig.isEmpty()) {
            throw new TagNotFoundException("Tag is not present");

        }
        List<PigWeightRecord> weightRecords = pigWeightRecordRepository.findByPig(pig.get());

        List<WeightResponseData> weightResponse = new ArrayList<>();
        for (PigWeightRecord pigWeightRecord : weightRecords) {
            WeightResponseData recordResponse = new WeightResponseData();
            recordResponse.setDate(pigWeightRecord.getDateAdded().toString().split(" ")[0]);
            recordResponse.setWeight(pigWeightRecord.getWeight());

            weightResponse.add(recordResponse);
        }
        BodyResponse bodyResponse = new BodyResponse();
        bodyResponse.setProcessed(true);
        bodyResponse.setResult(weightResponse);
        bodyResponse.setStatusCode(HttpStatus.OK);

        return bodyResponse;
    }


    private boolean pigExists(long id) {
        return pigRepository.existsById(id);
    }

}
