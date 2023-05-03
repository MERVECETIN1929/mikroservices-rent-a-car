package com.kodlamaio.inventoryservice.business.dto.response.update;

import com.kodlamaio.inventoryservice.entities.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UpdateCarResponse {
    private UUID id;
    private int modelYear;
    private String plate;
    private State state;
    private double dailyPrice;
    private String carModelName;
}
