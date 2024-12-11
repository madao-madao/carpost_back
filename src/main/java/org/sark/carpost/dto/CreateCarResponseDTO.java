package org.sark.carpost.dto;

import lombok.Getter;
import lombok.Setter;
import org.sark.carpost.entity.car.CarBrandEntity;
import org.sark.carpost.entity.car.CarGenerationEntity;
import org.sark.carpost.entity.car.CarModelEntity;

import java.util.List;

@Setter
@Getter
public class CreateCarResponseDTO {

    private List<CarModelEntity> carModels;

    private List<CarBrandEntity> carBrands;

    private List<CarGenerationEntity> carGenerations;
}
