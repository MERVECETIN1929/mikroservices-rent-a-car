package com.kodlamaio.inventoryservice.business.concretes;

import com.kodlamaio.inventoryservice.business.abstracts.ModelService;
import com.kodlamaio.inventoryservice.business.dto.request.create.CreateModelRequest;
import com.kodlamaio.inventoryservice.business.dto.request.update.UpdateModelRequest;
import com.kodlamaio.inventoryservice.business.dto.response.create.CreateModelResponse;
import com.kodlamaio.inventoryservice.business.dto.response.get.GetAllModelsResponse;
import com.kodlamaio.inventoryservice.business.dto.response.get.GetModelResponse;
import com.kodlamaio.inventoryservice.business.dto.response.update.UpdateModelResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor

public class ModelManager implements ModelService {
    @Override
    public List<GetAllModelsResponse> getAll() {
        return null;
    }

    @Override
    public GetModelResponse getById(UUID id) {
        return null;
    }

    @Override
    public CreateModelResponse add(CreateModelRequest request) {
        return null;
    }

    @Override
    public UpdateModelResponse update(UUID id, UpdateModelRequest request) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }
}
