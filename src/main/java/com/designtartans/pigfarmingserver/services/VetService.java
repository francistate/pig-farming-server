package com.designtartans.pigfarmingserver.services;

import java.util.HashMap;
import java.util.Map;

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
import com.designtartans.pigfarmingserver.utils.JwtService;

@Service
public class VetService implements VetServiceInterface {

    @Autowired
    private VetRepository vetRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private VetShopServiceInterface vetShopService;

    @Autowired
    private JwtService jwtService;

    @Override
    public BodyResponse createVet(VetDto vetDto) throws PhoneNumberAlreadyExistException, InvalidShopIdException {

        VetShop vetShop = vetShopService.findShopById(vetDto.getVet_shop_id());
        if (vetShop != null) {
            Vet vet = new Vet();
            User user = userService
                    .createUser(UserDto.builder().firstname(vetDto.getFirstname()).lastname(vetDto.getLastname())
                            .phoneNumber(vetDto.getPhoneNumber()).password(vetDto.getPassword()).role("VET").build());

            vet.setUser(user);
            vet.setVetShop(vetShop);
            vetRepository.save(vet);

            String jwt = jwtService.generateToken(user);

            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("vetID", vet.getId());
            responseBody.put("userID", user.getUserId());
            responseBody.put("token", jwt);
            responseBody.put("firstname", user.getFirstName());
            responseBody.put("lastname", user.getLastName());
            responseBody.put("phoneNumber", user.getPhoneNumber());
            responseBody.put("role", user.getRole());

            BodyResponse response = new BodyResponse();
            response.setProcessed(true);
            response.setResult(responseBody);
            response.setStatusCode(HttpStatus.CREATED);
            return response;
        } else {
            throw new InvalidShopIdException("Invalid Shop ID Provided");
        }

    }

}
