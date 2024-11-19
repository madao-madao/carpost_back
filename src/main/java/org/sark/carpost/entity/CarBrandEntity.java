package org.sark.carpost.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;

@Getter
@Data
@Entity
@Table(name = "car_brand")
public class CarBrandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany
    private ArrayList<CarModelEntity> carModel;

}
