package com.designtartans.pigfarmingserver.services;

import com.designtartans.pigfarmingserver.dto.BodyResponse;
import com.designtartans.pigfarmingserver.dto.MinisterDto;
import com.designtartans.pigfarmingserver.exceptions.PhoneNumberAlreadyExistException;

public interface MinisterServiceInterface {
    BodyResponse createMinister(MinisterDto ministerDto) throws PhoneNumberAlreadyExistException;
}
