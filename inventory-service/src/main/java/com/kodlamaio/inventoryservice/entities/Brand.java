package com.kodlamaio.inventoryservice.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name="brands")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    // todo relationalship
    @OneToMany(mappedBy = "brand")
    private List<Model> models;

}
