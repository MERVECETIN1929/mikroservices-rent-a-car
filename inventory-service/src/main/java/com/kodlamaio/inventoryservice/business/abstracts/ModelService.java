package com.kodlamaio.inventoryservice.business.abstracts;

import com.kodlamaio.inventoryservice.business.dto.request.create.CreateModelRequest;
import com.kodlamaio.inventoryservice.business.dto.request.update.UpdateModelRequest;
import com.kodlamaio.inventoryservice.business.dto.response.create.CreateModelResponse;
import com.kodlamaio.inventoryservice.business.dto.response.get.GetAllModelsResponse;
import com.kodlamaio.inventoryservice.business.dto.response.get.GetModelResponse;
import com.kodlamaio.inventoryservice.business.dto.response.update.UpdateModelResponse;

import java.util.List;
import java.util.UUID;

public interface ModelService {
    List<GetAllModelsResponse> getAll();
    GetModelResponse getById(UUID id);
    CreateModelResponse add(CreateModelRequest request);
    UpdateModelResponse update(UUID id, UpdateModelRequest request);
    void delete(UUID id);
}
