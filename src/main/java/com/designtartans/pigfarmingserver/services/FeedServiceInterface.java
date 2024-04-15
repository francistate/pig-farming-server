package com.designtartans.pigfarmingserver.services;

import com.designtartans.pigfarmingserver.dto.BodyResponse;
import com.designtartans.pigfarmingserver.dto.FeedDto;

public interface FeedServiceInterface {

    BodyResponse createFeed(FeedDto feedDto);
}
