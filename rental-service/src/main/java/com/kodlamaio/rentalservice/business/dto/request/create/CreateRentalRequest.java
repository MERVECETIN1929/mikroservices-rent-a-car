package com.kodlamaio.rentalservice.business.dto.request.create;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CreateRentalRequest {
    @NotNull
    private UUID carId;
    @Min(1)
    private double daliyPrice;
    @Min(1)
    private int rentedForDays;
}

