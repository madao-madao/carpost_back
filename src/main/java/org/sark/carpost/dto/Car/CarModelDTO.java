package org.sark.carpost.dto.Car;

import org.sark.carpost.entity.CarBrandEntity;

import lombok.Data;

@Data
public class CarModelDTO {
    String name;
    CarBrandEntity brand;
}
