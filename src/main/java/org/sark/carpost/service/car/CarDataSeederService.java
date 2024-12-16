package org.sark.carpost.service.car;

import java.util.List;

import org.sark.carpost.dto.CarApi.CarBrandResponseDTO;
import org.sark.carpost.entity.CarBrandEntity;
import org.springframework.stereotype.Service;

@Service
public class CarDataSeederService {
    private final CarApiService carApiService;
    private final CarBrandService carBrandService;
    private final CarModelService carModelService;

    public CarDataSeederService(CarApiService carApiService, CarBrandService carBrandService,
            CarModelService carModelService) {
        this.carApiService = carApiService;
        this.carBrandService = carBrandService;
        this.carModelService = carModelService;
    }

    public void seed() {
        List<CarBrandResponseDTO> carBrands = this.carApiService.fetchCarData();
        carBrands.forEach(brand -> {
            CarBrandEntity brandEntity = carBrandService.store(brand);
            brand.getModels().forEach(model -> {
                carModelService.store(model, brandEntity);
            });
        });
    }

}
