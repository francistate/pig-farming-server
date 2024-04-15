package com.designtartans.pigfarmingserver.services;

import com.designtartans.pigfarmingserver.dto.BodyResponse;
import com.designtartans.pigfarmingserver.dto.PigWeightRecordDto;

public interface PigWeightRecordServiceInterface {
    BodyResponse createPigWeightRecord(PigWeightRecordDto pigWeightRecordDto);
}
