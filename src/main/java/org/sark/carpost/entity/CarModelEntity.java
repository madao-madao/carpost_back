package org.sark.carpost.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;

@Getter
@Data
@Entity
@Table(name = "car_model")
public class CarModelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "car_brand_id")
    private Long carBrandId;

    @OneToMany
    private ArrayList<CarGenerationEntity> carGenerations;
}
