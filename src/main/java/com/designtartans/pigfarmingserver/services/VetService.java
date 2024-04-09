package com.designtartans.pigfarmingserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.designtartans.pigfarmingserver.dto.BodyResponse;
import com.designtartans.pigfarmingserver.dto.UserDto;
import com.designtartans.pigfarmingserver.dto.VetDto;
import com.designtartans.pigfarmingserver.exceptions.InvalidShopIdException;
import com.designtartans.pigfarmingserver.exceptions.PhoneNumberAlreadyExistException;
import com.designtartans.pigfarmingserver.model.User;
import com.designtartans.pigfarmingserver.model.Vet;
import com.designtartans.pigfarmingserver.model.VetShop;
import com.designtartans.pigfarmingserver.repository.VetRepository;

@Service
public class VetService implements VetServiceInterface {

    @Autowired
    private VetRepository vetRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private VetShopServiceInterface vetShopService;

    @Override
    public BodyResponse createVet(VetDto vetDto) throws PhoneNumberAlreadyExistException, InvalidShopIdException {

        VetShop vetShop = vetShopService.findShopById(vetDto.getVet_shop_id());
        if (vetShop != null) {
            Vet vet = new Vet();
            User user = userService
                    .createUser(UserDto.builder().firstName(vetDto.getFirstName()).lastName(vetDto.getLastName())
                            .phoneNumber(vetDto.getPhoneNumber()).password(vetDto.getPassword()).role("VET").build());

            vet.setUser(user);
            vet.setVetShop(vetShop);
            vetRepository.save(vet);

            BodyResponse response = new BodyResponse();
            response.setProcessed(true);
            response.setResult(vet);
            response.setStatusCode(HttpStatus.CREATED);
            return response;
        } else {
            throw new InvalidShopIdException("Invalid Shop ID Provided");
        }

    }

}
