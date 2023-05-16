package com.kodlamaio.inventoryservice.business.dto.request.create;

import com.kodlamaio.inventoryservice.entities.enums.State;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateCarRequest {
    @Min(value = 2000)
    private int modelYear;
    @NotNull
    @NotBlank
    // TODO: Add Regex
    private String plate;
    @Min(value = 1)
    private double dailyPrice;
    @NotNull
    private UUID modelId;
}
