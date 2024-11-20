package org.sark.carpost.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;


@Getter
@Data
@Entity
@Table(name = "car")
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String vin;

    private String plate;

    @OneToOne
    private CarBrandEntity brand;

    @OneToOne
    private CarModelEntity model;

    @OneToOne
    private CarGenerationEntity generation;
}

