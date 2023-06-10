package com.kodlamaio.inventoryservice.business.dto.request.update;

import com.kodlamaio.inventoryservice.entities.enums.State;
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
@Setter
@Getter
public class UpdateCarRequest {
    @Min(value = 2000)
    private int modelYear;

    @NotBlank
    // TODO: Add Regex
    private String plate;
    @Min(value = 1)
    private double dailyPrice;
    @NotNull

    private UUID modelId;
    @NotNull

    private State state;
}
