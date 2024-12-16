package org.sark.carpost.service.car;

import org.sark.carpost.dto.Car.CarModelDTO;
import org.sark.carpost.dto.CarApi.CarModelResponseDTO;
import org.sark.carpost.entity.CarBrandEntity;
import org.sark.carpost.entity.CarModelEntity;
import org.sark.carpost.repository.CarModelRepository;
import org.springframework.stereotype.Service;

@Service
public class CarModelService {
    private final CarModelRepository CarModelRepository;
    private CarModelDTO CarModelDTO;

    public CarModelService(CarModelRepository CarModelRepository) {
        this.CarModelRepository = CarModelRepository;
        this.CarModelDTO = new CarModelDTO();
    }

    public Long store(CarModelDTO CarModelDTO) {
        return store();
    }

    public Long store(CarModelResponseDTO CarModelResponseDTO, CarBrandEntity brand) {
        CarModelDTO.setName(CarModelResponseDTO.getName());
        CarModelDTO.setBrand(brand);
        return store();
    }

    private Long store() {
        CarModelEntity CarModel = new CarModelEntity();
        CarModel.setName(CarModelDTO.getName());
        CarModel.setCarBrand(CarModelDTO.getBrand());
        return CarModelRepository.save(CarModel).getId();
    }
}
