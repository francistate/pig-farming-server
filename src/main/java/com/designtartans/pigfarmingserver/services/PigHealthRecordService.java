package com.designtartans.pigfarmingserver.services;

import com.designtartans.pigfarmingserver.dto.BodyResponse;
import com.designtartans.pigfarmingserver.dto.PigHealthRecordDto;
import com.designtartans.pigfarmingserver.model.PigHealthRecord;
import com.designtartans.pigfarmingserver.repository.PigHealthRecordRepository;
import com.designtartans.pigfarmingserver.repository.PigRepository;
import com.designtartans.pigfarmingserver.repository.VetRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PigHealthRecordService implements PigHealthRecordServiceInterface{

    private PigRepository pigRepository;

    private VetRepository vetRepository;

    private PigHealthRecordRepository pigHealthRecordRepository;

    @Override
    public BodyResponse createPigHealthRecord(PigHealthRecordDto pigHealthRecordDto) {
        boolean vetExists = vetExists(pigHealthRecordDto.getVetId());
        boolean pigExists = pigExists(pigHealthRecordDto.getPigId());

        if (!vetExists || !pigExists) {
            BodyResponse response = new BodyResponse();
            response.setProcessed(false);
            if (!vetExists && !pigExists) {
                response.setResult("Vet & Pig not found");
            } else if(!pigExists) {
                response.setResult("Pig not found");
            } else {
                response.setResult("Vet not found");
            }

            response.setStatusCode(HttpStatus.NOT_FOUND);
            return response;
        }

        // create pig health record
        PigHealthRecord pigHealthRecord = new PigHealthRecord();
        pigHealthRecord.setPig(pigRepository.findById(pigHealthRecordDto.getPigId()).get());
        pigHealthRecord.setTreatment(pigHealthRecordDto.getTreatment());
        pigHealthRecord.setDescription(pigHealthRecordDto.getDescription());
        pigHealthRecord.setVet(vetRepository.findById(pigHealthRecordDto.getVetId()).get());
        pigHealthRecordRepository.save(pigHealthRecord);

        BodyResponse response = new BodyResponse();
        response.setProcessed(true);
        response.setResult(pigHealthRecord);
        response.setStatusCode(HttpStatus.CREATED);
        return response;
    }



    // check if pig exists
    private boolean pigExists(long id) {
        return pigRepository.existsById(id);
    }

    private boolean vetExists(long id) {
        return vetRepository.existsById(id);
    }

}
