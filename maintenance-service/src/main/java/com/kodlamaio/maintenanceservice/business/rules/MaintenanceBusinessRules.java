package com.kodlamaio.maintenanceservice.business.rules;

import com.kodlamaio.commonpackage.utils.constans.Message;
import com.kodlamaio.commonpackage.utils.dto.response.ClientResponse;
import com.kodlamaio.commonpackage.utils.exceptions.BusinessException;
import com.kodlamaio.maintenanceservice.api.clients.CarClient;
import com.kodlamaio.maintenanceservice.repository.MaintenanceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class MaintenanceBusinessRules {
    private final MaintenanceRepository maintenanceRepository;
    private final CarClient carClient;
    public void existsMaintenanceById(UUID id){
        if(!maintenanceRepository.existsMaintenanceById(id)){
            throw new BusinessException(Message.Maintenance.NotExists);
        }
    }
    public void checkCarAvailabilityForMaintenance(UUID carId){
        ClientResponse clientResponse=carClient.checkIfCarAvailable(carId);
        if(!clientResponse.isSuccess()){
            throw new BusinessException(clientResponse.getMessage());
        }
    }
    public void checkCarInMaintenanceAndRepairedFalse(UUID carId){
        if(!maintenanceRepository.existsMaintenanceByCarIdAndIsRepairedFalse(carId)){
            throw new BusinessException(Message.Maintenance.CarNotExists);
        }
    }
}
