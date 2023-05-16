package com.kodlamaio.inventoryservice.api.controller;

import com.kodlamaio.inventoryservice.business.abstracts.BrandService;
import com.kodlamaio.inventoryservice.business.dto.request.create.CreateBrandRequest;
import com.kodlamaio.inventoryservice.business.dto.request.update.UpdateBrandRequest;
import com.kodlamaio.inventoryservice.business.dto.response.create.CreateBrandResponse;
import com.kodlamaio.inventoryservice.business.dto.response.get.GetAllBrandsResponse;
import com.kodlamaio.inventoryservice.business.dto.response.get.GetBrandResponse;
import com.kodlamaio.inventoryservice.business.dto.response.update.UpdateBrandResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/Brand")
@AllArgsConstructor
public class BrandController {
    private final BrandService service;
    @GetMapping
    public List<GetAllBrandsResponse> getAll(){
        return service.getAll();
    }
    @GetMapping("/{id}")
    public GetBrandResponse getById(@PathVariable UUID id){
        return service.getById(id);
    }
    @PutMapping("/{id}")

    public UpdateBrandResponse update(@PathVariable UUID id, @Valid @RequestBody UpdateBrandRequest request){
        return service.update(id,request);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateBrandResponse add(@Valid @RequestBody CreateBrandRequest request){
        return service.add(request);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id){
        service.delete(id);
    }
}
