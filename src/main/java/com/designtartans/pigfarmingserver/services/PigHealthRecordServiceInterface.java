package com.designtartans.pigfarmingserver.services;

import com.designtartans.pigfarmingserver.dto.BodyResponse;
import com.designtartans.pigfarmingserver.dto.PigHealthRecordDto;
import com.designtartans.pigfarmingserver.exceptions.PigNotFoundException;
import com.designtartans.pigfarmingserver.exceptions.VetNotFoundException;

public interface PigHealthRecordServiceInterface {
    BodyResponse createPigHealthRecord(PigHealthRecordDto pigHealthRecordDto);

    BodyResponse getPigHealthRecordsByTag(String tag) throws PigNotFoundException;

    BodyResponse getPigHealthRecordsByVet(Long vetId) throws VetNotFoundException;
}
