package com.kodlamaio.inventoryservice.business.dto.request.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UpdateModelRequest {
    @NotBlank
    @NotNull
    @Size(min = 2, max = 20)
    private String name;
    @NotNull
    @NotBlank
    private UUID brandId;
}
