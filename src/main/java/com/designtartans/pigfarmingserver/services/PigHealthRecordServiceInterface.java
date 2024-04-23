package com.designtartans.pigfarmingserver.services;

import com.designtartans.pigfarmingserver.dto.BodyResponse;
import com.designtartans.pigfarmingserver.dto.PigHealthRecordDto;
import com.designtartans.pigfarmingserver.exceptions.PigNotFoundException;

public interface PigHealthRecordServiceInterface {
    BodyResponse createPigHealthRecord(PigHealthRecordDto pigHealthRecordDto);

    BodyResponse getPigHealthRecordsByTag(String tag) throws PigNotFoundException;
}
