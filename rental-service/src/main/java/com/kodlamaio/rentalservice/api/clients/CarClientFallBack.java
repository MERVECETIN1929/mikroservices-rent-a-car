package com.kodlamaio.rentalservice.api.clients;

import com.kodlamaio.commonpackage.utils.dto.response.ClientResponse;
import com.kodlamaio.commonpackage.utils.exceptions.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
/*resilience4j.retry:
 configs:
  default:
    maxAttempts: 11
    waitDuration: 7s
 @Retry(name="default")*/
public class CarClientFallBack implements CarClient{
    @Override

    public ClientResponse checkIfCarAvailable(UUID carId)  {
        log.info("inventory service is down");
        throw new BusinessException("INVENTORY SERVICE NOT AVAILABLE RIGHT NOW");
    }
}
