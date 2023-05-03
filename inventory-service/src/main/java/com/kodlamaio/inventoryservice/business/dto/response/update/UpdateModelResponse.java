package com.kodlamaio.inventoryservice.business.dto.response.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UpdateModelResponse {
    private UUID id;
    private String name;
    private String modelBrandName;

}
