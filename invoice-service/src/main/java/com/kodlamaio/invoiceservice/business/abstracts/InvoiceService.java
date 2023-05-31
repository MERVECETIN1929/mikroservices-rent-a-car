package com.kodlamaio.invoiceservice.business.abstracts;

import com.kodlamaio.invoiceservice.business.dto.CreateInvoiceResponse;
import com.kodlamaio.invoiceservice.business.dto.GetAllInvoicesResponse;
import com.kodlamaio.invoiceservice.business.dto.GetInvoiceResponse;
import com.kodlamaio.invoiceservice.entities.Invoice;

import java.util.List;
import java.util.UUID;

public interface InvoiceService {
    GetInvoiceResponse getById(UUID id);
    void delete(UUID id);
    List<GetAllInvoicesResponse> getAll();
    CreateInvoiceResponse add(Invoice invoice);
}
