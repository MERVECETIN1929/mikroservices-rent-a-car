package com.kodlamaio.invoiceservice.business.concretes;


import com.kodlamaio.commonpackage.utils.mappers.ModelMapperService;
import com.kodlamaio.invoiceservice.business.abstracts.InvoiceService;
import com.kodlamaio.invoiceservice.business.dto.CreateInvoiceResponse;
import com.kodlamaio.invoiceservice.business.dto.GetAllInvoicesResponse;
import com.kodlamaio.invoiceservice.business.dto.GetInvoiceResponse;
import com.kodlamaio.invoiceservice.entities.Invoice;
import com.kodlamaio.invoiceservice.repository.InvoiceRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InvoiceManager implements InvoiceService {
    private final ModelMapperService mapper;
    private final InvoiceRepository repository;

    @Override
    public GetInvoiceResponse getById(UUID id) {
        //todo: business rules
        var invoice=mapper.forResponse().map(repository.findById(id).orElseThrow(),GetInvoiceResponse.class);
        return invoice;
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public List<GetAllInvoicesResponse> getAll() {
        var invoices=repository.findAll();
        var response=invoices.stream().map(invoice->mapper.forResponse().map(invoice, GetAllInvoicesResponse.class)).toList();
        return response;
    }

    @Override
    public CreateInvoiceResponse add(Invoice invoice) {
        invoice.setId(UUID.randomUUID());
        var save_invoice=repository.save(invoice);
        return mapper.forResponse().map(save_invoice, CreateInvoiceResponse.class);
    }
}
