package com.kodlamaio.inventoryservice.business.dto.response.get;

import com.kodlamaio.inventoryservice.entities.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetCarResponse {
    private UUID id;
    private int modelYear;
    private String plate;
    private State state;
    private double dailyPrice;
    private String carModelName;

}
