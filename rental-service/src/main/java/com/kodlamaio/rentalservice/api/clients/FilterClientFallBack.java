package com.kodlamaio.rentalservice.api.clients;

import com.kodlamaio.commonpackage.utils.dto.response.ClientResponse;
import com.kodlamaio.commonpackage.utils.exceptions.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Slf4j
@Component
public class FilterClientFallBack implements FilterClient{
    @Override
    public ClientResponse getByCarId(UUID carId) {
        log.info("filter service is down");
        throw new BusinessException("FILTER SERVICE NOT AVAILABLE RIGHT NOW");
    }
}
