package org.sark.carpost.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
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

    @ManyToOne
    @JsonBackReference
    private UserEntity user;
}

