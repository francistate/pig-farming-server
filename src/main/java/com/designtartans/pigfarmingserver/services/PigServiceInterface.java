package com.designtartans.pigfarmingserver.services;

import com.designtartans.pigfarmingserver.dto.BodyResponse;
import com.designtartans.pigfarmingserver.dto.PigDto;
import com.designtartans.pigfarmingserver.exceptions.FarmNotFoundException;
import com.designtartans.pigfarmingserver.exceptions.PigNotFoundException;

public interface PigServiceInterface {
    BodyResponse createPig(PigDto pigDto) throws PigNotFoundException, FarmNotFoundException;

    BodyResponse getPigsByFarm(Long farmId) throws FarmNotFoundException;
}
