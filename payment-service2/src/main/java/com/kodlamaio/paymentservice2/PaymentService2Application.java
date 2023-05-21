package com.kodlamaio.paymentservice2;

import com.kodlamaio.commonpackage.utils.constans.Paths;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication(scanBasePackages = {Paths.ConfigurationBasePackage,Paths.Payment.ServiceBasePackage})
public class PaymentService2Application {

    public static void main(String[] args) {
        SpringApplication.run(PaymentService2Application.class, args);
    }

}
