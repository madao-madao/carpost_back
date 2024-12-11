package org.sark.carpost.entity.car;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "car_model")
public class CarModelEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "car_brand_id")
    @JsonBackReference
    private CarBrandEntity carBrand;


    @OneToMany(mappedBy = "carModel", cascade = CascadeType.ALL)
//    @JsonManagedReference
    @JsonIgnore
    private List<CarGenerationEntity> carGenerations;
}
