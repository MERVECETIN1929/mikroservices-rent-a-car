package com.kodlamaio.inventoryservice.api.controller;

import com.kodlamaio.inventoryservice.business.abstracts.ModelService;
import com.kodlamaio.inventoryservice.business.abstracts.ModelService;
import com.kodlamaio.inventoryservice.business.dto.request.create.CreateModelRequest;
import com.kodlamaio.inventoryservice.business.dto.request.update.UpdateModelRequest;
import com.kodlamaio.inventoryservice.business.dto.response.create.CreateModelResponse;
import com.kodlamaio.inventoryservice.business.dto.response.get.GetAllModelsResponse;
import com.kodlamaio.inventoryservice.business.dto.response.get.GetModelResponse;
import com.kodlamaio.inventoryservice.business.dto.response.update.UpdateModelResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/model")
@AllArgsConstructor
public class ModelController {
    private final ModelService service;
    @GetMapping
    public List<GetAllModelsResponse> getAll(){
        return service.getAll();
    }
    @GetMapping("/{id}")
    public GetModelResponse getById(@PathVariable UUID id){
        return service.getById(id);
    }
    @PutMapping("/{id}")

    public UpdateModelResponse update(@PathVariable UUID id, @Valid @RequestBody UpdateModelRequest request){
        return service.update(id,request);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateModelResponse add(@Valid @RequestBody CreateModelRequest request){
        return service.add(request);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id){
        service.delete(id);
    }
}
