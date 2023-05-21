package com.kodlamaio.maintenanceservice.business.dto.response.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class GetMaintenanceResponse {
    private UUID id;
    private UUID carId;
    private LocalDateTime dateIn;
    private LocalDateTime dateOut;
    private double cost;
    private boolean isRepaired;
    private String description;
}
