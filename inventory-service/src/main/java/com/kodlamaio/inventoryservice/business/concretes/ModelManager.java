package com.kodlamaio.inventoryservice.business.concretes;

import com.kodlamaio.commonpackage.utils.mappers.ModelMapperService;
import com.kodlamaio.inventoryservice.business.abstracts.ModelService;
import com.kodlamaio.inventoryservice.business.dto.request.create.CreateModelRequest;
import com.kodlamaio.inventoryservice.business.dto.request.update.UpdateModelRequest;
import com.kodlamaio.inventoryservice.business.dto.response.create.CreateBrandResponse;
import com.kodlamaio.inventoryservice.business.dto.response.create.CreateModelResponse;
import com.kodlamaio.inventoryservice.business.dto.response.get.GetAllBrandsResponse;
import com.kodlamaio.inventoryservice.business.dto.response.get.GetAllModelsResponse;
import com.kodlamaio.inventoryservice.business.dto.response.get.GetBrandResponse;
import com.kodlamaio.inventoryservice.business.dto.response.get.GetModelResponse;
import com.kodlamaio.inventoryservice.business.dto.response.update.UpdateBrandResponse;
import com.kodlamaio.inventoryservice.business.dto.response.update.UpdateModelResponse;
import com.kodlamaio.inventoryservice.entities.Brand;
import com.kodlamaio.inventoryservice.entities.Model;
import com.kodlamaio.inventoryservice.repository.ModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor

public class ModelManager implements ModelService {
    private final ModelRepository repository;
    private final ModelMapperService mapper;
    @Override
    public List<GetAllModelsResponse> getAll() {
        var models=repository.findAll();
        var response=models.stream().map(brand -> mapper.forResponse().map(brand, GetAllModelsResponse.class)).toList();
        return response;
    }

    @Override
    public GetModelResponse getById(UUID id) {
        var model=repository.findById(id).orElseThrow();
        var response=mapper.forResponse().map(model, GetModelResponse.class);
        return response;
    }

    @Override
    public CreateModelResponse add(CreateModelRequest request) {

        var model=mapper.forRequest().map(request, Model.class);
        model.setId(null);
        repository.save(model);
        var response=mapper.forResponse().map(model, CreateModelResponse.class);
        return response;
    }

    @Override
    public UpdateModelResponse update(UUID id, UpdateModelRequest request) {
        var model=mapper.forRequest().map(request,Model.class);
        model.setId(id);
        repository.save(model);
        var response=mapper.forResponse().map(model, UpdateModelResponse.class);
        return response;
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);

    }
}
