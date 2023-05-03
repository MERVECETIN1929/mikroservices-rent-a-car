package com.kodlamaio.inventoryservice.business.abstracts;

import com.kodlamaio.inventoryservice.business.dto.request.create.CreateBrandRequest;
import com.kodlamaio.inventoryservice.business.dto.request.update.UpdateBrandRequest;
import com.kodlamaio.inventoryservice.business.dto.response.create.CreateBrandResponse;
import com.kodlamaio.inventoryservice.business.dto.response.get.GetAllBrandsResponse;
import com.kodlamaio.inventoryservice.business.dto.response.get.GetBrandResponse;
import com.kodlamaio.inventoryservice.business.dto.response.update.UpdateBrandResponse;

import java.util.List;
import java.util.UUID;

public interface BrandService {
    List<GetAllBrandsResponse> getAll();
    GetBrandResponse getById(UUID id);
    CreateBrandResponse add(CreateBrandRequest request);
    UpdateBrandResponse update(UUID id, UpdateBrandRequest request);
    void delete(UUID id);
}
