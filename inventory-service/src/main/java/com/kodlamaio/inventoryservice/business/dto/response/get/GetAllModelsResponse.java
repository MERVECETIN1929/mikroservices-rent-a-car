package com.kodlamaio.inventoryservice.business.dto.response.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetAllModelsResponse {
    private UUID id;
    private String name;
    private String modelBrandName;

}
