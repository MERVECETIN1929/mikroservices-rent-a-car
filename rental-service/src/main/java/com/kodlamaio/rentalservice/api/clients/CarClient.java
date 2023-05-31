package com.kodlamaio.rentalservice.api.clients;

import com.kodlamaio.commonpackage.utils.dto.response.ClientCarResponse;
import com.kodlamaio.commonpackage.utils.dto.response.ClientResponse;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name="inventory-service",fallback = CarClientFallBack.class)//inventory service için çalışacak opsiyonel

public interface CarClient {

    @Retry(name="default")
    //@CircuitBreaker(name="inventory-service",fallbackMethod = "checkIfCarAvailable")
    @GetMapping(value="api/cars/check-car-available/{carId}")
    ClientResponse checkIfCarAvailable(@PathVariable UUID carId);
    @GetMapping(value="api/cars/{carId}")
    ClientCarResponse getCarById(@PathVariable UUID carId);
}