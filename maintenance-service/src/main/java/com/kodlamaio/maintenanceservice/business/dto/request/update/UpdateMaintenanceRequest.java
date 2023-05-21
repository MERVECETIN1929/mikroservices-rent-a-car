package com.kodlamaio.maintenanceservice.business.dto.request.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UpdateMaintenanceRequest {

    private UUID carId;


    private double cost;

    private String description;
}
