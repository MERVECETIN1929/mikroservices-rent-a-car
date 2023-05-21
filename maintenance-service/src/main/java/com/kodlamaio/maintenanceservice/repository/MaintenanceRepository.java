package com.kodlamaio.maintenanceservice.repository;

import com.kodlamaio.maintenanceservice.entities.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MaintenanceRepository extends JpaRepository<Maintenance, UUID> {
    boolean existsMaintenanceById(UUID id);
    Maintenance findMaintenanceByCarIdAndRepairedFalse(UUID carId);

    boolean existsMaintenanceByCarIdAndRepairedFalse(UUID carId);
}