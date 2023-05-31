package com.kodlamaio.invoiceservice;

import com.kodlamaio.commonpackage.utils.constans.Paths;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Path;

@SpringBootApplication(scanBasePackages = {Paths.ConfigurationBasePackage,Paths.Invoice.ServiceBasePackage})
public class InvoiceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvoiceServiceApplication.class, args);
	}

}
