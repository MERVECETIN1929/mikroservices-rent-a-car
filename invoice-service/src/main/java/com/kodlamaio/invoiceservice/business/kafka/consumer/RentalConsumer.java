package com.kodlamaio.invoiceservice.business.kafka.consumer;

import com.kodlamaio.commonpackage.events.invoice.InvoiceCreatedEvent;
import com.kodlamaio.commonpackage.utils.mappers.ModelMapperService;
import com.kodlamaio.invoiceservice.business.abstracts.InvoiceService;
import com.kodlamaio.invoiceservice.entities.Invoice;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RentalConsumer {
    private final ModelMapperService mapper;
    private final InvoiceService service;
    @KafkaListener(groupId = "invoice-create",topics = "invoice-created")
    public void consumer(InvoiceCreatedEvent event){
        var invoice=mapper.forRequest().map(event, Invoice.class);
        service.add(invoice);
    }
}
