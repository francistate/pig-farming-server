package com.designtartans.pigfarmingserver.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.designtartans.pigfarmingserver.dto.BodyResponse;
import com.designtartans.pigfarmingserver.dto.VetShopDto;
import com.designtartans.pigfarmingserver.exceptions.ExistingVetShopNameException;
import com.designtartans.pigfarmingserver.model.VetShop;
import com.designtartans.pigfarmingserver.repository.VetShopRepository;

@Service
public class VetShopService implements VetShopServiceInterface {

    @Autowired
    private VetShopRepository vetShopRepository;

    @Override
    public BodyResponse createVetShop(VetShopDto shopDto) throws ExistingVetShopNameException {
        if (!isVetShopExists(shopDto.getVetShopName())) {
            VetShop vetShop = new VetShop();
            vetShop.setDistrict(shopDto.getDistrict());
            vetShop.setProvince(shopDto.getProvince());
            vetShop.setVetShopName(shopDto.getVetShopName());
            vetShop.setEmail(shopDto.getEmail());
            vetShop.setPhoneNumber(shopDto.getPhoneNumber());

            vetShopRepository.save(vetShop);
            BodyResponse response = new BodyResponse();
            response.setProcessed(true);
            response.setStatusCode(HttpStatus.CREATED);
            response.setResult(vetShop);
            return response;
        } else {
            throw new ExistingVetShopNameException("Vet Shop Name exists");
        }

    }

    private boolean isVetShopExists(String vetShopName) {
        Optional<VetShop> vetShop = vetShopRepository.findByVetShopName(vetShopName);
        return vetShop.isEmpty() ? false : true;
    }

    @Override
    public BodyResponse findAllShops() {
        List<VetShop> vetShops = vetShopRepository.findAll();

        BodyResponse response = new BodyResponse();
        response.setProcessed(true);
        response.setStatusCode(HttpStatus.OK);
        response.setResult(vetShops);
        return response;
    }

    @Override
    public VetShop findShopById(long id) {
        Optional<VetShop> vetShop = vetShopRepository.findById(id);
        if (vetShop.isEmpty()) {
            return null;
        } else {
            return vetShop.get();
        }
    }

}
