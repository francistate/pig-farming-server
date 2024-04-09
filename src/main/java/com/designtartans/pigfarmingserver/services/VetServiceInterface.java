package com.designtartans.pigfarmingserver.services;

import com.designtartans.pigfarmingserver.dto.BodyResponse;
import com.designtartans.pigfarmingserver.dto.VetDto;
import com.designtartans.pigfarmingserver.exceptions.InvalidShopIdException;
import com.designtartans.pigfarmingserver.exceptions.PhoneNumberAlreadyExistException;

public interface VetServiceInterface {
    BodyResponse createVet(VetDto vetDto) throws PhoneNumberAlreadyExistException, InvalidShopIdException;
}
