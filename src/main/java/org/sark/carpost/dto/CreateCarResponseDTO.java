package org.sark.carpost.dto;

import lombok.Getter;
import lombok.Setter;
import org.sark.carpost.entity.CarBrandEntity;
import org.sark.carpost.entity.CarGenerationEntity;
import org.sark.carpost.entity.CarModelEntity;

import java.util.List;

@Setter
@Getter
public class CreateCarResponseDTO {

    private List<CarModelEntity> carModels;

    private List<CarBrandEntity> carBrands;

    private List<CarGenerationEntity> carGenerations;
}
