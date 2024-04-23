package com.designtartans.pigfarmingserver.services;

import com.designtartans.pigfarmingserver.dto.BodyResponse;
import com.designtartans.pigfarmingserver.dto.PigWeightRecordDto;
import com.designtartans.pigfarmingserver.exceptions.PigNotFoundException;
import com.designtartans.pigfarmingserver.exceptions.TagNotFoundException;

public interface PigWeightRecordServiceInterface {
    BodyResponse createPigWeightRecord(PigWeightRecordDto pigWeightRecordDto) throws PigNotFoundException;


    BodyResponse getPigWeightRecords(String tag) throws TagNotFoundException;

}
