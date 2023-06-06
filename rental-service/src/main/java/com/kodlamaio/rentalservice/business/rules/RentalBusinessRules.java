package com.kodlamaio.rentalservice.business.rules;



import com.kodlamaio.commonpackage.utils.dto.request.PaymentRentalRequest;
import com.kodlamaio.commonpackage.utils.exceptions.BusinessException;

import com.kodlamaio.rentalservice.api.clients.CarClient;
import com.kodlamaio.rentalservice.api.clients.PaymentClient;
import com.kodlamaio.rentalservice.repository.RentalRepository;

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
    public void ensureCarIsAvailable(UUID carId){
        var response=carClient.checkIfCarAvailable(carId);
        if(!response.isSuccess()){
            //System.err.println("ben geldim inventory");
            throw new BusinessException(response.getMessage());
        }
    }
    public void makeRentalPayment(PaymentRentalRequest request){
        var response= paymentClient.makePayment(request);
        if(!response.isSuccess()){
            //System.err.println("ben geldim payment");
            throw new BusinessException(response.getMessage());
        }
    }

}
