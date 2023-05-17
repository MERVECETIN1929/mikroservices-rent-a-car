package com.kodlamaio.rentalservice.business.abstracts;

import com.kodlamaio.rentalservice.business.dto.request.create.CreateRentalRequest;
import com.kodlamaio.rentalservice.business.dto.request.update.UpdateRentalRequest;
import com.kodlamaio.rentalservice.business.dto.response.create.CreateRentalResponse;
import com.kodlamaio.rentalservice.business.dto.response.get.GetAllRentalsResponse;
import com.kodlamaio.rentalservice.business.dto.response.get.GetRentalResponse;
import com.kodlamaio.rentalservice.business.dto.response.update.UpdateRentalResponse;

import java.util.List;
import java.util.UUID;

public interface RentalService {
    List<GetAllRentalsResponse> getAll();
    GetRentalResponse getById(UUID id);
    CreateRentalResponse add(CreateRentalRequest request);
    UpdateRentalResponse update(UUID id, UpdateRentalRequest request);
    void delete(UUID id);
}