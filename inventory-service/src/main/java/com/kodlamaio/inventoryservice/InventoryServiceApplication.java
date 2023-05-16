package com.kodlamaio.inventoryservice;

import com.kodlamaio.commonpackage.utils.constans.Paths;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
//başka bir projedeki beanlere ulaşmak için önce o pakette neredeki beanlere ulaşmak istediğini sonra
// onu hangi projede kullanacağını scanBasePackages ile belirt.
@SpringBootApplication(scanBasePackages = {Paths.ConfigurationBasePackage,Paths.Inventory.ServiceBasePackage})
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

}
