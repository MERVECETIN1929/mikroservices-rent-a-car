package com.kodlamaio.maintenanceservice;

import com.kodlamaio.commonpackage.utils.constans.Paths;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication(scanBasePackages = {Paths.ConfigurationBasePackage,Paths.Maintenance.ServiceBasePackage})
public class MaintenanceServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MaintenanceServiceApplication.class, args);
    }

}