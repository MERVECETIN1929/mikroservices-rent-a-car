package com.kodlamaio.maintenanceservice.business.dto.response.create;

import jakarta.annotation.security.DenyAll;
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
public class CreateMaintenanceResponse {

    private UUID carId;

    private double cost;

    private String description;
}

