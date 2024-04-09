package com.designtartans.pigfarmingserver.services;

import com.designtartans.pigfarmingserver.dto.BodyResponse;
import com.designtartans.pigfarmingserver.dto.FarmDto;
import com.designtartans.pigfarmingserver.model.Farm;

public interface FarmServiceInterface {
    public BodyResponse createFarm(FarmDto farmDto);

    public Farm getFarm(long id);
}
