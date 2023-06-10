package com.kodlamaio.filterservice.business.concretes;

import com.kodlamaio.commonpackage.utils.mappers.ModelMapperService;
import com.kodlamaio.filterservice.business.abstracts.FilterService;
import com.kodlamaio.filterservice.business.dto.response.GetAllFiltersResponse;
import com.kodlamaio.filterservice.business.dto.response.GetFilterResponse;
import com.kodlamaio.filterservice.entities.Filter;
import com.kodlamaio.filterservice.repository.FilterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor//final olanları yapıcı blokta newler
public class FilterManager implements FilterService {
    private final FilterRepository repository;
    private final ModelMapperService mapper;
    @Override
    public List<GetAllFiltersResponse> getAll() {
        var filters =repository.findAll();
        var response=filters.stream().map(filter -> mapper.forResponse().map(filter,GetAllFiltersResponse.class)).toList();
        return response;
    }

    @Override
    public GetFilterResponse getById(String id) {
        var filter=repository.findById(id).orElseThrow();
        var result=mapper.forResponse().map(filter, GetFilterResponse.class);
        return result;
    }

    @Override
    public void add(Filter filter) {
        repository.save(filter);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAllByBrandId(UUID brandId) {
        repository.deleteAllByBrandId(brandId);
    }

    @Override
    public void deleteAllByModelId(UUID modelId) {

    }
    public void deleteByCarId(UUID id){
        repository.deleteAllByCarId(id);
    }

    @Override
    public Filter getFilterByCarId(UUID carId) {
        return repository.findByCarId(carId);
    }
}
