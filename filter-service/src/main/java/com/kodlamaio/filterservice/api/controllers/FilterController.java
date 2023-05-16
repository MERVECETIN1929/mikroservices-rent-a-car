package com.kodlamaio.filterservice.api.controllers;

import com.kodlamaio.filterservice.business.abstracts.FilterService;
import com.kodlamaio.filterservice.business.dto.response.GetAllFiltersResponse;
import com.kodlamaio.filterservice.entities.Filter;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/filters")
public class FilterController {
    private final FilterService service;
    @GetMapping
    public List<GetAllFiltersResponse> getAll(){
        return service.getAll();
    }

}
/* nosql veri tabanınında otomatik tablo oluşması için yazılmış bir kod bloğudur

 @PostConstruct
    public void createDb(){
        service.add(new Filter());
    }*/