package com.kodlamaio.rentalservice.business.rules;


import com.kodlamaio.commonpackage.utils.dto.request.Payment;
import com.kodlamaio.commonpackage.utils.dto.request.PaymentRentalRequest;
import com.kodlamaio.commonpackage.utils.exceptions.BusinessException;

import com.kodlamaio.rentalservice.api.clients.CarClient;
import com.kodlamaio.rentalservice.api.clients.PaymentClient;
import com.kodlamaio.rentalservice.repository.RentalRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RentalBusinessRules {
    private final RentalRepository repository;
    private final PaymentClient paymentClient;
    private final CarClient carClient;

    public void checkIfRentalExists(UUID id){
        if(!repository.existsById(id)){
            //todo bussinesExceptions
            throw new BusinessException("RENTAL_NOT_EXISTS");
        }
    }
    public void ensureCarIsAvailable(UUID id){
        var response=carClient.checkIfCarAvailable(id);
        if(!response.isSuccess()){
            throw new BusinessException(response.getMessage());
        }
    }
    public void makeRentalPayment(PaymentRentalRequest request){
        var response= paymentClient.makePayment(request);
        if(!response.isSuccess()){
            throw new BusinessException(response.getMessage());
        }
    }

}
