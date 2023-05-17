package com.kodlamaio.inventoryservice.api.controller;

import com.kodlamaio.inventoryservice.business.abstracts.CarService;
import com.kodlamaio.inventoryservice.business.dto.request.create.CreateCarRequest;
import com.kodlamaio.inventoryservice.business.dto.request.update.UpdateCarRequest;
import com.kodlamaio.inventoryservice.business.dto.response.create.CreateCarResponse;
import com.kodlamaio.inventoryservice.business.dto.response.get.GetAllCarsResponse;
import com.kodlamaio.inventoryservice.business.dto.response.get.GetCarResponse;
import com.kodlamaio.inventoryservice.business.dto.response.update.UpdateCarResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/cars")
@AllArgsConstructor
public class CarController {
    private final CarService service;
    @GetMapping
    public List<GetAllCarsResponse> getAll(){
        return service.getAll();
    }
    @GetMapping("/{id}")
    public GetCarResponse getById(@PathVariable  UUID id){
        return service.getById(id);
    }
    @PutMapping("/{id}")

    public UpdateCarResponse update(@PathVariable UUID id, @Valid @RequestBody UpdateCarRequest request){
        return service.update(id,request);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCarResponse add(@Valid @RequestBody CreateCarRequest request){
        return service.add(request);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id){
        service.delete(id);
    }
    @GetMapping("/check-car-available/{id}")
    public void checkIfCarAvailable(@PathVariable UUID id){
        service.checkIfCarAvailable(id);
    }

}
