package com.kodlamaio.filterservice.business.kafka.consumer;

import com.kodlamaio.commonpackage.events.rental.RentalCreatedEvent;
import com.kodlamaio.commonpackage.events.rental.RentalDeletedEvent;
import com.kodlamaio.filterservice.business.abstracts.FilterService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class RentalConsumer {
    private final FilterService service;
    @KafkaListener(topics = "rental-created",groupId = "filter-rental-create")
    public void consume(RentalCreatedEvent event){
        var filter=service.getFilterByCarId(event.getCarId());
        filter.setState("Rented");
        service.add(filter);
        log.info("rental created consume info {}",event);
    }
    @KafkaListener(topics = "rental-delted",groupId = "filter-rental-deleted")
    public void consume(RentalDeletedEvent event){
        var filter=service.getFilterByCarId(event.getCarId());
        filter.setState("Available");
        service.add(filter);
        log.info("rental deleted consume info {}",event);
    }
}
