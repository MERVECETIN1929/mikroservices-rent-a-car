package com.kodlamaio.rentalservice.business.dto.request.update;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UpdateRentalRequest {
    @NotNull
    private UUID carId;
    @Min(1)
    private double daliyPrice;
    @Min(1)
    private int rentedForDays;
    @NotNull
    private LocalDate rentedAt;
}
