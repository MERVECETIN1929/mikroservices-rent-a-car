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
public class UpdateBrandResponse {
    private UUID id;
    private String name;
}

