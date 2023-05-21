package com.kodlamaio.maintenanceservice.business.abstracts;

import com.kodlamaio.maintenanceservice.business.dto.request.create.CreateMaintenanceRequest;
import com.kodlamaio.maintenanceservice.business.dto.request.update.UpdateMaintenanceRequest;
import com.kodlamaio.maintenanceservice.business.dto.response.create.CreateMaintenanceResponse;
import com.kodlamaio.maintenanceservice.business.dto.response.get.GetAllMaintenancesResponse;
import com.kodlamaio.maintenanceservice.business.dto.response.get.GetMaintenanceResponse;
import com.kodlamaio.maintenanceservice.business.dto.response.update.UpdateMaintenanceResponse;

import java.util.List;
import java.util.UUID;

public interface MaintenanceService {
    List<GetAllMaintenancesResponse> getAll();
    GetMaintenanceResponse getById(UUID id);
    CreateMaintenanceResponse add(CreateMaintenanceRequest request);
    UpdateMaintenanceResponse update(UUID id, UpdateMaintenanceRequest request);
    void delete(UUID id);
    void returnTheCarFromMaintenance(UUID carId);
}
