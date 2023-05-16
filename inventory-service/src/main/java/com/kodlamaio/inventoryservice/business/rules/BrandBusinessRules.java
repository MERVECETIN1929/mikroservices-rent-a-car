package com.kodlamaio.inventoryservice.business.rules;

import com.kodlamaio.inventoryservice.repository.BrandRepository;
import com.kodlamaio.inventoryservice.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class BrandBusinessRules {
    private final BrandRepository repository;
    public void checkIfBrandExists(UUID id){
        if(!repository.existsById(id)){
            //todo bussinesExceptions
            throw new RuntimeException("BRAND_NOT_EXISTS");
        }
    }
}
