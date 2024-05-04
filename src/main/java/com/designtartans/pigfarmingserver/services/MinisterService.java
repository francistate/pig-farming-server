package com.designtartans.pigfarmingserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.HashMap;

import com.designtartans.pigfarmingserver.dto.BodyResponse;
import com.designtartans.pigfarmingserver.dto.MinisterDto;
import com.designtartans.pigfarmingserver.dto.UserDto;
import com.designtartans.pigfarmingserver.exceptions.PhoneNumberAlreadyExistException;
import com.designtartans.pigfarmingserver.model.Minister;
import com.designtartans.pigfarmingserver.model.User;
import com.designtartans.pigfarmingserver.repository.MinisterRepository;
import com.designtartans.pigfarmingserver.utils.JwtService;

@Service
public class MinisterService implements MinisterServiceInterface {

    @Autowired
    private MinisterRepository ministerRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Override
    public BodyResponse createMinister(MinisterDto ministerDto) throws PhoneNumberAlreadyExistException {
        User user = userService.createUser(UserDto.builder().firstname(ministerDto.getFirstname())
                .lastname(ministerDto.getLastname()).phoneNumber(ministerDto.getPhoneNumber())
                .password(ministerDto.getPassword()).role("MINISTER").build());
        Minister minister = new Minister();
        minister.setUser(user);
        minister.setMinistry(ministerDto.getMinistry());

        ministerRepository.save(minister);

        String token = jwtService.generateToken(user);

        Map<String, Object> response = new HashMap<>();
        response.put("ID", minister.getId());
        response.put("firstname", minister.getUser().getFirstName());
        response.put("lastname", minister.getUser().getLastName());
        response.put("phoneNumber", minister.getUser().getPhoneNumber());
        response.put("role", minister.getUser().getRole());
        response.put("ministry", minister.getMinistry());
        response.put("token", token);

        BodyResponse bodyResponse = new BodyResponse();
        bodyResponse.setProcessed(true);
        bodyResponse.setStatusCode(HttpStatus.CREATED);
        bodyResponse.setResult(response);

        return bodyResponse;

    }
}
