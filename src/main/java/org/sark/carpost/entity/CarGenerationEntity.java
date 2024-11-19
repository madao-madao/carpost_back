package org.sark.carpost.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
@Entity
@Table(name = "car_generation")
public class CarGenerationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "car_model_id")
    private Long carModelId;
}
