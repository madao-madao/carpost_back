package org.sark.carpost.service.car;

import org.sark.carpost.dto.Car.CarBrandDTO;
import org.sark.carpost.dto.CarApi.CarBrandResponseDTO;
import org.sark.carpost.entity.CarBrandEntity;
import org.sark.carpost.repository.CarBrandRepository;
import org.springframework.stereotype.Service;

@Service
public class CarBrandService {
    private final CarBrandRepository carBrandRepository;
    private CarBrandDTO carBrandDTO;

    public CarBrandService(CarBrandRepository carBrandRepository) {
        this.carBrandRepository = carBrandRepository;
        this.carBrandDTO = new CarBrandDTO();
    }

    public CarBrandEntity store(CarBrandDTO carBrandDTO) {
        return store();
    }

    public CarBrandEntity store(CarBrandResponseDTO carBrandResponseDTO) {
        carBrandDTO.setName(carBrandResponseDTO.getName());
        return store();
    }

    private CarBrandEntity store() {
        CarBrandEntity carBrand = new CarBrandEntity();
        carBrand.setName(carBrandDTO.getName());
        return carBrandRepository.save(carBrand);
    }
}
