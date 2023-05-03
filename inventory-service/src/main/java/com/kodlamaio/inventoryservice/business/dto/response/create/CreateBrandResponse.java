package com.kodlamaio.inventoryservice.business.dto.response.create;

import com.kodlamaio.inventoryservice.entities.enums.State;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateBrandResponse {
    private UUID id;
    private String name;

}

