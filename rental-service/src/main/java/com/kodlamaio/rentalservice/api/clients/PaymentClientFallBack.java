package com.kodlamaio.rentalservice.api.clients;

import com.kodlamaio.commonpackage.utils.dto.request.PaymentRentalRequest;
import com.kodlamaio.commonpackage.utils.dto.response.ClientResponse;
import com.kodlamaio.commonpackage.utils.exceptions.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentClientFallBack implements PaymentClient{
    @Override
    public ClientResponse makePayment(PaymentRentalRequest payment) {
        log.info("PAYMENT service is down");
        throw new BusinessException("PAYMENT SERVICE NOT AVAILABLE RIGHT NOW");
    }
}
