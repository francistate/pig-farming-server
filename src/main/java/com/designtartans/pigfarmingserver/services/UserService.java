package com.designtartans.pigfarmingserver.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.designtartans.pigfarmingserver.dto.AuthenticationRequest;
import com.designtartans.pigfarmingserver.dto.BodyResponse;
import com.designtartans.pigfarmingserver.dto.UserDto;
import com.designtartans.pigfarmingserver.exceptions.PhoneNumberAlreadyExistException;
import com.designtartans.pigfarmingserver.model.Farmer;
import com.designtartans.pigfarmingserver.model.User;
import com.designtartans.pigfarmingserver.model.Vet;
import com.designtartans.pigfarmingserver.repository.FarmerRepository;
import com.designtartans.pigfarmingserver.repository.UserRepository;
import com.designtartans.pigfarmingserver.repository.VetRepository;
import com.designtartans.pigfarmingserver.utils.JwtService;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private FarmerRepository farmerRepository;

    @Autowired
    private VetRepository vetRepository;

    public User createUser(UserDto userDto) throws PhoneNumberAlreadyExistException {
        if (!isUserExistsByPhoneNumber(userDto.getPhoneNumber())) {
            User user = new User();
            user.setFirstName(userDto.getFirstname());
            user.setLastName(userDto.getLastname());
            user.setPhoneNumber(userDto.getPhoneNumber());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setRole(userDto.getRole());
            return userRepository.save(user);
        } else {
            throw new PhoneNumberAlreadyExistException("Existing phone number");
        }

    }

    public BodyResponse authenticate(AuthenticationRequest request) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getPhoneNumber(), request.getPassword()));

        User user = userRepository.findByPhoneNumber(request.getPhoneNumber()).get();
        String jwt = jwtService.generateToken(user);
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("token", jwt);
        responseBody.put("role", user.getRole());
        responseBody.put("firstname", user.getFirstName());
        responseBody.put("lastname", user.getLastName());
        responseBody.put("phoneNumber", user.getPhoneNumber());

        if (user.getRole().equalsIgnoreCase("FARMER")) {
            Farmer farmer = farmerRepository.findByUser(user).get();
            if (farmer.getFarm() == null) {
                responseBody.put("FarmID", null);
            } else {
                responseBody.put("FarmID", farmer.getFarm().getId());
            }

        } else if (user.getRole().equalsIgnoreCase("VET")) {
            Vet vet = vetRepository.findByUser(user).get();
            responseBody.put("vetID", vet.getId());
            responseBody.put("vetShop", vet.getVetShop().getVetShopName());
        }

        BodyResponse response = new BodyResponse();
        response.setStatusCode(HttpStatus.OK);
        response.setResult(responseBody);
        response.setProcessed(true);

        return response;
    }

    private boolean isUserExistsByPhoneNumber(String phoneNumber) {
        Optional<User> user = userRepository.findByPhoneNumber(phoneNumber);
        return user.isEmpty() ? false : true;
    }
}
