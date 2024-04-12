package com.designtartans.pigfarmingserver.services;

import com.designtartans.pigfarmingserver.dto.BodyResponse;
import com.designtartans.pigfarmingserver.dto.PigDto;

public interface PigServiceInterface {
    BodyResponse createPig(PigDto pigDto);
}
