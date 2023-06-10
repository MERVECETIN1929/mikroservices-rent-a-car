package com.kodlamaio.inventoryservice.business.rules;

import com.kodlamaio.commonpackage.utils.constans.Message;
import com.kodlamaio.commonpackage.utils.exceptions.BusinessException;
import com.kodlamaio.inventoryservice.entities.enums.State;
import com.kodlamaio.inventoryservice.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CarBusinessRules {
    private final CarRepository repository;
    public void checkIfCarExists(UUID id){
        if(!repository.existsById(id)){
            //todo bussinesExceptions
            throw new BusinessException(Message.Car.NotExists);
        }
    }
    public void checkCarAvailability(UUID id){
        var car=repository.findById(id).orElseThrow();
        if(!car.getState().equals(State.Available)){
            throw new BusinessException(Message.Rental.CarNotAvailable);
        }
    }
}
