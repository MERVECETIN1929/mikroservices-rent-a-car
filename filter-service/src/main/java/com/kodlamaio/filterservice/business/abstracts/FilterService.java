package com.kodlamaio.filterservice.business.abstracts;

import com.kodlamaio.filterservice.business.dto.response.GetAllFiltersResponse;
import com.kodlamaio.filterservice.business.dto.response.GetFilterResponse;
import com.kodlamaio.filterservice.entities.Filter;

import java.util.List;
import java.util.UUID;

public interface FilterService {
    List<GetAllFiltersResponse> getAll();
    GetFilterResponse getById(String id);
    void add(Filter filter);
    void delete(String id);
    void deleteAllByBrandId(UUID brandId);//bulk
    void deleteAllByModelId(UUID modelId);
    void deleteByCarId(UUID id);
    Filter getFilterByCarId(UUID carId);
}
