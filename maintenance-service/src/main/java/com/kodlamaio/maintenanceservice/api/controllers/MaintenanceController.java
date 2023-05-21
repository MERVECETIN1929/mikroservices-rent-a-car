package com.kodlamaio.maintenanceservice.api.controllers;

import com.kodlamaio.commonpackage.utils.dto.ClientResponse;
import com.kodlamaio.maintenanceservice.business.abstracts.MaintenanceService;
import com.kodlamaio.maintenanceservice.business.dto.request.create.CreateMaintenanceRequest;
import com.kodlamaio.maintenanceservice.business.dto.request.update.UpdateMaintenanceRequest;
import com.kodlamaio.maintenanceservice.business.dto.response.create.CreateMaintenanceResponse;
import com.kodlamaio.maintenanceservice.business.dto.response.get.GetAllMaintenancesResponse;
import com.kodlamaio.maintenanceservice.business.dto.response.get.GetMaintenanceResponse;
import com.kodlamaio.maintenanceservice.business.dto.response.update.UpdateMaintenanceResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@AllArgsConstructor
@RestController
@RequestMapping("api/maintenances")
public class MaintenanceController {
    private final MaintenanceService service;
    @GetMapping
    public List<GetAllMaintenancesResponse> getAll(){
        return service.getAll();
    }
    @GetMapping("/{id}")
    public GetMaintenanceResponse getById(@PathVariable UUID id){
        return service.getById(id);
    }
    @PutMapping("/{id}")

    public UpdateMaintenanceResponse update(@PathVariable UUID id, @Valid @RequestBody UpdateMaintenanceRequest request){
        return service.update(id,request);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateMaintenanceResponse add(@Valid @RequestBody CreateMaintenanceRequest request){
        return service.add(request);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id){
        service.delete(id);
    }
    @PutMapping("/{carId}")
    public void returnTheCarFromMaintenance(@RequestParam UUID carId){
        service.returnTheCarFromMaintenance(carId);
    }
}
