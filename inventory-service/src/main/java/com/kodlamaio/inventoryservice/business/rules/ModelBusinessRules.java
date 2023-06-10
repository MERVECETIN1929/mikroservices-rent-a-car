package com.kodlamaio.inventoryservice.business.rules;

import com.kodlamaio.commonpackage.utils.constans.Message;
import com.kodlamaio.commonpackage.utils.exceptions.BusinessException;
import com.kodlamaio.inventoryservice.repository.CarRepository;
import com.kodlamaio.inventoryservice.repository.ModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ModelBusinessRules {
    private final ModelRepository repository;
    public void checkIfModelExists(UUID id){
        if(!repository.existsById(id)){
            //todo bussinesExceptions
            throw new BusinessException(Message.Model.NotExists);
        }
    }
}
