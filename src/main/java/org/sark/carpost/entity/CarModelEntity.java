package org.sark.carpost.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import java.util.List;

@Getter
@Data
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
