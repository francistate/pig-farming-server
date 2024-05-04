package com.designtartans.pigfarmingserver.services;

import com.designtartans.pigfarmingserver.dto.BodyResponse;
import com.designtartans.pigfarmingserver.dto.PigDto;
import com.designtartans.pigfarmingserver.exceptions.FarmNotFoundException;
import com.designtartans.pigfarmingserver.exceptions.PigNotFoundException;
import com.designtartans.pigfarmingserver.model.Farm;
import com.designtartans.pigfarmingserver.model.Pig;
import com.designtartans.pigfarmingserver.model.PigStatus;
import com.designtartans.pigfarmingserver.model.PigWeightRecord;
import com.designtartans.pigfarmingserver.repository.FarmRepository;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import com.designtartans.pigfarmingserver.repository.PigRepository;
import com.designtartans.pigfarmingserver.repository.PigWeightRecordRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PigService implements PigServiceInterface {

    @Autowired
    private PigRepository pigRepository;

    @Autowired
    private FarmRepository farmRepository;

    @Autowired
    private PigWeightRecordRepository pigWeightRecordRepository;

    @Override
    public BodyResponse createPig(PigDto pigDto) throws PigNotFoundException, FarmNotFoundException {

        if (!farmExists(pigDto.getFarmId())) {
            throw new FarmNotFoundException("Farm not found");
        }

        if (pigDto.getParentId() != null) {
            if (!pigExists(pigDto.getParentId())) {
                throw new PigNotFoundException("Parent pig not found");
            }
        }
        Farm farm = farmRepository.findById(pigDto.getFarmId()).get();

        Pig pig = new Pig();
        pig.setLatestWeight(pigDto.getLatestWeight());
        pig.setGender(pigDto.getGender());
        pig.setBreed(pigDto.getBreed());
        pig.setParentId(pigDto.getParentId());
        pig.setDateOfBirth(pigDto.getDateOfBirth());
        pig.setPigStatus(PigStatus.ACTIVE);
        pig.setFarm(farm);
        pig.setTag(generateTag());
        pigRepository.save(pig);

        PigWeightRecord record = new PigWeightRecord();
        record.setPig(pig);
        record.setWeight(pig.getLatestWeight());

        pigWeightRecordRepository.save(record);

        BodyResponse response = new BodyResponse();
        response.setStatusCode(HttpStatus.CREATED);
        response.setProcessed(true);
        response.setResult(pig);
        return response;
    }

    @Override
    public BodyResponse getPigsByFarm(Long farmId) throws FarmNotFoundException {
        if (!farmExists(farmId)) {
            throw new FarmNotFoundException("Farm not found");
        }

        BodyResponse response = new BodyResponse();
        response.setStatusCode(HttpStatus.OK);
        response.setProcessed(true);
        response.setResult(pigRepository.findByFarmId(farmId));
        return response;
    }

    public BodyResponse updatePig(long pigId, PigDto pigDto) throws PigNotFoundException {
        // Check if the pig exists
        if (!pigExists(pigId)) {
            throw new PigNotFoundException("Pig not found");
        }

        // Retrieve the existing pig from the database
        Pig existingPig = pigRepository.findById(pigId).orElse(null);
        if (existingPig == null) {
            throw new PigNotFoundException("Pig not found");
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

        // Save the updated pig to the database
        pigRepository.save(existingPig);

        // Prepare and return the response
        BodyResponse response = new BodyResponse();
        response.setStatusCode(HttpStatus.OK);
        response.setProcessed(true);
        response.setResult(existingPig);
        return response;
    }

    BodyResponse getPigById(long pigId) throws PigNotFoundException {

        // Retrieve the pig from the database
        Pig pig = pigRepository.findById(pigId).orElse(null);
        if (pig == null) {
            throw new PigNotFoundException("Pig not found");
        }

        // Prepare and return the response
        BodyResponse response = new BodyResponse();
        response.setStatusCode(HttpStatus.OK);
        response.setProcessed(true);
        response.setResult(pig);
        return response;
    }

    public BodyResponse getPigByTag(String tag) throws PigNotFoundException {

        Pig pig = pigRepository.findByTag(tag).orElse(null);
        if (pig == null) {
            throw new PigNotFoundException("Pig not found");
        }

        BodyResponse response = new BodyResponse();
        response.setStatusCode(HttpStatus.OK);
        response.setProcessed(true);
        response.setResult(pigRepository.findByTag(tag));
        return response;
    }

    public BodyResponse getPigGenderCountForFarm(Long farmId) throws FarmNotFoundException {

        Farm farm = farmRepository.findById(farmId).orElse(null);
        if (farm == null) {
            throw new FarmNotFoundException("Farm not found");
        }

        BodyResponse response = new BodyResponse();
        response.setStatusCode(HttpStatus.OK);
        response.setProcessed(true);
        response.setResult(parseCount(pigRepository.countPigsByGenderForAFarm(farmId)));
        return response;

    }

    public BodyResponse countAllActivePigs() {
        BodyResponse response = new BodyResponse();
        response.setStatusCode(HttpStatus.OK);
        response.setProcessed(true);
        response.setResult(pigRepository.countAllActivePigs());
        return response;
    }

    public BodyResponse countActivePigsPerProvince() {
        BodyResponse response = new BodyResponse();
        response.setStatusCode(HttpStatus.OK);
        response.setProcessed(true);
        response.setResult(pigRepository.countActivePigsPerProvince());
        return response;
    }

    public BodyResponse getBreedCountForFarm(Long farmId) throws FarmNotFoundException {
        Farm farm = farmRepository.findById(farmId).orElse(null);
        if (farm == null) {
            throw new FarmNotFoundException("Farm not found");
        }

        BodyResponse response = new BodyResponse();
        response.setStatusCode(HttpStatus.OK);
        response.setProcessed(true);
        response.setResult(parseCount(pigRepository.countPigsByBreedForAFarm(farmId)));
        return response;
    }

    public BodyResponse getBreedCountForAllFarmsCombined() {
        BodyResponse response = new BodyResponse();
        response.setStatusCode(HttpStatus.OK);
        response.setProcessed(true);
        response.setResult(parseCount(pigRepository.countPigsByBreedForAllFarmsCombined()));
        return response;
    }

    // check if farm exists
    private boolean farmExists(long id) {
        return farmRepository.existsById(id);
    }

    // check if pig exists
    private boolean pigExists(long id) {
        return pigRepository.existsById(id);
    }

    private List<Map<String, Object>> parseCount(List<Object[]> genderCount) {
        // Process into JSON
        List<Map<String, Object>> jsonData = new ArrayList<>();
        for (Object[] entry : genderCount) {
            Map<String, Object> mapEntry = new HashMap<>();
            mapEntry.put(entry[0].toString(), entry[1]);

            jsonData.add(mapEntry);
        }

        //sort the list by alphabetical order of the keys
        Collections.sort(jsonData, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                String key1 = o1.keySet().iterator().next();
                String key2 = o2.keySet().iterator().next();
                return key1.compareTo(key2);
            }
        });

        return jsonData;
    }




    private String generateTag() {
        String tagName = "";
        // generate random string
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 5;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        tagName = "P-" + generatedString.toUpperCase();

        if (pigRepository.findByTag(tagName).isEmpty()) {

            return tagName;
        } else {
            return generateTag();
        }
    }
}
