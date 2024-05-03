package com.designtartans.pigfarmingserver.services;

import com.designtartans.pigfarmingserver.dto.BodyResponse;
import com.designtartans.pigfarmingserver.dto.PigHealthRecordDto;
import com.designtartans.pigfarmingserver.dto.PigHealthRecordReturnDto;
import com.designtartans.pigfarmingserver.exceptions.PigNotFoundException;
import com.designtartans.pigfarmingserver.exceptions.VetNotFoundException;
import com.designtartans.pigfarmingserver.model.Pig;
import com.designtartans.pigfarmingserver.model.PigHealthRecord;
import com.designtartans.pigfarmingserver.model.Vet;
import com.designtartans.pigfarmingserver.repository.PigHealthRecordRepository;
import com.designtartans.pigfarmingserver.repository.PigRepository;
import com.designtartans.pigfarmingserver.repository.VetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PigHealthRecordService implements PigHealthRecordServiceInterface{

    @Autowired
    private PigRepository pigRepository;
    @Autowired
    private VetRepository vetRepository;
    @Autowired
    private PigHealthRecordRepository pigHealthRecordRepository;

    @Override
    public BodyResponse createPigHealthRecord(PigHealthRecordDto pigHealthRecordDto)  {
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


    public BodyResponse getPigHealthRecordsByTag(String tag) throws PigNotFoundException {
        if (!pigExists(tag)) {
            throw new PigNotFoundException("Pig Not found");
        }

        Pig pig = pigRepository.findByTag(tag).get();
        BodyResponse response = new BodyResponse();
        response.setProcessed(true);
        response.setResult(pigHealthRecordRepository.findByPigId(pig.getId()));
        response.setStatusCode(HttpStatus.OK);
        return response;

    }


    public BodyResponse getPigHealthRecordsByVet(Long vetId) throws VetNotFoundException{
        Vet vet = vetRepository.findById(vetId).orElse(null);
        if (vet == null) {
            throw new VetNotFoundException("Vet not found");
        }


        List<PigHealthRecord> pigHealthRecords = pigHealthRecordRepository.findByVetId(vet.getId());
        // sort by date, newest record first
        pigHealthRecords.sort((p1, p2) -> p2.getDate().compareTo(p1.getDate()));
        List<PigHealthRecordReturnDto> pigHealthRecordDtos = mapToPigHealthRecordReturnDtos(pigHealthRecords);

        BodyResponse response = new BodyResponse();
        response.setProcessed(true);
        response.setResult(pigHealthRecordDtos);
        response.setStatusCode(HttpStatus.OK);
        return response;
    }

    private List<PigHealthRecordReturnDto> mapToPigHealthRecordReturnDtos(List<PigHealthRecord> pigHealthRecords) {
        return pigHealthRecords.stream()
                .map(this::convertToPigHealthRecordDto)
                .collect(Collectors.toList());
    }

    private PigHealthRecordReturnDto convertToPigHealthRecordDto(PigHealthRecord pigHealthRecord) {
        PigHealthRecordReturnDto pigHealthRecordReturnDto = new PigHealthRecordReturnDto();
        pigHealthRecordReturnDto.setId(pigHealthRecord.getId());
        pigHealthRecordReturnDto.setPig(pigHealthRecord.getPig());
        pigHealthRecordReturnDto.setVetId(pigHealthRecord.getVet().getId());
        pigHealthRecordReturnDto.setDescription(pigHealthRecord.getDescription());
        pigHealthRecordReturnDto.setTreatment(pigHealthRecord.getTreatment());
        pigHealthRecordReturnDto.setDate(pigHealthRecord.getDate());
        return pigHealthRecordReturnDto;

    }


    // check if pig exists
    private boolean pigExists(long id) {
        return pigRepository.existsById(id);
    }

    private boolean pigExists(String tag) {
        return pigRepository.findByTag(tag).isPresent();
    }

    private boolean vetExists(long id) {
        Optional<Vet> vet = vetRepository.findById(id);
        return vet.isPresent();
    }

}
