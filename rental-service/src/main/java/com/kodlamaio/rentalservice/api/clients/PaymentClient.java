package com.kodlamaio.rentalservice.api.clients;

import com.kodlamaio.commonpackage.utils.dto.request.Payment;
import com.kodlamaio.commonpackage.utils.dto.request.PaymentRentalRequest;
import com.kodlamaio.commonpackage.utils.dto.response.ClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="payment-service2",fallback=PaymentClientFallBack.class)
public interface PaymentClient {
    @PostMapping("/api/payments/check")
    ClientResponse makePayment(@RequestBody PaymentRentalRequest payment);
}
