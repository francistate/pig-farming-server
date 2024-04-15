package com.designtartans.pigfarmingserver.services;

import com.designtartans.pigfarmingserver.dto.BodyResponse;
import com.designtartans.pigfarmingserver.dto.PigHealthRecordDto;

public interface PigHealthRecordServiceInterface {
    BodyResponse createPigHealthRecord(PigHealthRecordDto pigHealthRecordDto);
}
