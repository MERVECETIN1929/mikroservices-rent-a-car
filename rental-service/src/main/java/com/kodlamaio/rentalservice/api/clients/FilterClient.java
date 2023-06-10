package com.kodlamaio.rentalservice.api.clients;

import com.kodlamaio.commonpackage.utils.dto.response.ClientCarResponse;
import com.kodlamaio.commonpackage.utils.dto.response.ClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@FeignClient(name="filter-service")
public interface FilterClient {
    @GetMapping(value = "/api/filters/getByCarId")
    ClientResponse getByCarId(@RequestParam UUID carId);
}
