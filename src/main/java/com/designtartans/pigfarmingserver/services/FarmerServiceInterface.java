package com.designtartans.pigfarmingserver.services;

import com.designtartans.pigfarmingserver.dto.BodyResponse;
import com.designtartans.pigfarmingserver.dto.FarmerDto;
import com.designtartans.pigfarmingserver.exceptions.PhoneNumberAlreadyExistException;

public interface FarmerServiceInterface {
    BodyResponse createFarmer(FarmerDto farmerDto) throws PhoneNumberAlreadyExistException;
}
