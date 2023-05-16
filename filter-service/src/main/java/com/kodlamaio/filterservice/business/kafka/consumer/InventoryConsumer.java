package com.kodlamaio.filterservice.business.kafka.consumer;

import com.kodlamaio.commonpackage.events.BrandDeletedEvent;
import com.kodlamaio.commonpackage.events.CarCreatedEvent;
import com.kodlamaio.commonpackage.events.CarDeletedEvent;
import com.kodlamaio.commonpackage.utils.mappers.ModelMapperService;
import com.kodlamaio.filterservice.business.abstracts.FilterService;
import com.kodlamaio.filterservice.entities.Filter;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryConsumer {
    private final FilterService service;
    private final ModelMapperService mapper;
    @KafkaListener(
            topics = "car-created",
            groupId = "car-creat"
    )
    public void consume(CarCreatedEvent event){
        var filter=mapper.forRequest().map(event, Filter.class);
        service.add(filter);
        log.info("car created event consume {}", event);
    }
    @KafkaListener(
            topics = "car-deleted",
            groupId = "car-delete"
    )
    public void consume(CarDeletedEvent event){
        var filter=mapper.forRequest().map(event, Filter.class);
        service.deleteByCarId(filter.getCarId());
        log.info("car deleted event consume {}", event);
    }
    @KafkaListener(
            topics = "brand-deleted",
            groupId = "brand-delete"
    )
    public void consume(BrandDeletedEvent event){
        //mapleme olmayacak çünkü brandın bir sürü modeli ve carı olabilir nasıl tek evete maplesin
        service.deleteAllByBrandId(event.getBrandId());
        log.info("brand deleted event consume {}", event);
    }
}
