package com.designtartans.pigfarmingserver.services;

import com.designtartans.pigfarmingserver.dto.BodyResponse;
import com.designtartans.pigfarmingserver.dto.FeedDto;
import com.designtartans.pigfarmingserver.exceptions.FarmNotFoundException;

public interface FeedServiceInterface {

    BodyResponse createFeed(FeedDto feedDto) throws FarmNotFoundException;

    BodyResponse getFeedsByFarm(Long farmId) throws FarmNotFoundException;
}
