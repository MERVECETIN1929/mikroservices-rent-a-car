package com.kodlamaio.inventoryservice.business.kafka.consumer;

import com.kodlamaio.commonpackage.events.maintenance.MaintenanceCreateEvent;
import com.kodlamaio.commonpackage.events.maintenance.MaintenanceDeleteEvent;
import com.kodlamaio.commonpackage.events.maintenance.MaintenanceReturnEvent;
import com.kodlamaio.inventoryservice.business.abstracts.CarService;
import com.kodlamaio.inventoryservice.entities.enums.State;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class MaintenanceConsumer {
    private final CarService carService;
    @KafkaListener(topics = "maintenance-created",groupId = "inventory-maintenance-create")
    public void consume(MaintenanceCreateEvent event){
        carService.changeStateByCarId(State.Maintenance,event.getCarId());
        log.info("maintenance created consume info {}",event);
    }
    @KafkaListener(topics = "maintenance-deleted",groupId = "inventory-maintenance-delete")
    public void consume(MaintenanceDeleteEvent event){
        carService.changeStateByCarId(State.Available,event.getCarId());
        log.info("maintenance delete consume info {}",event);
    }
    @KafkaListener(topics = "maintenance-return",groupId = "inventory-maintenance-return")
    public void consume(MaintenanceReturnEvent event){
        carService.changeStateByCarId(State.Available,event.getCarId());
        log.info("maintenance return consume info {}",event);
    }
}
