package org.sark.carpost.dto;

import lombok.Getter;
import lombok.Setter;
import org.sark.carpost.entity.CarBrandEntity;
import org.sark.carpost.entity.CarGenerationEntity;
import org.sark.carpost.entity.CarModelEntity;

import java.util.ArrayList;

@Setter
@Getter
public class CreateCarResponseDTO {

    private ArrayList<CarModelEntity> carModels;

    private ArrayList<CarBrandEntity> carBrands;

    private ArrayList<CarGenerationEntity> carGenerations;
}
