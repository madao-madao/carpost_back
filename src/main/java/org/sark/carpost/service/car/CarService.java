package org.sark.carpost.service.car;

import jakarta.persistence.EntityNotFoundException;
import org.sark.carpost.dto.CarProfileResponseDTO;
import org.sark.carpost.dto.CarUpdateRequestDTO;
import org.sark.carpost.dto.CreateCarResponseDTO;
import org.sark.carpost.dto.StoreCarProfileRequestDTO;
import org.sark.carpost.entity.car.CarBrandEntity;
import org.sark.carpost.entity.car.CarEntity;
import org.sark.carpost.entity.car.CarGenerationEntity;
import org.sark.carpost.entity.car.CarModelEntity;
import org.sark.carpost.entity.user.UserEntity;
import org.sark.carpost.repository.car.CarBrandRepository;
import org.sark.carpost.repository.car.CarGenerationRepository;
import org.sark.carpost.repository.car.CarModelRepository;
import org.sark.carpost.repository.car.CarRepository;
import org.sark.carpost.repository.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CarService {

    private final CarRepository carRepository;

    private final CarModelRepository carModelRepository;

    private final CarGenerationRepository carGenerationRepository;

    private final CarBrandRepository carBrandRepository;

    private final UserRepository userRepository;


    public CarService(CarRepository carRepository, CarModelRepository carModelRepository,
                      CarGenerationRepository carGenerationRepository, CarBrandRepository carBrandRepository, UserRepository userRepository) {
        this.carRepository = carRepository;
        this.carModelRepository = carModelRepository;
        this.carGenerationRepository = carGenerationRepository;
        this.carBrandRepository = carBrandRepository;
        this.userRepository = userRepository;
    }

    private static final Logger logger = LoggerFactory.getLogger(CarService.class);

    public CreateCarResponseDTO createCar() {
        List<CarBrandEntity> carBrands = carBrandRepository.findAll();
        List<CarModelEntity> carModels = carModelRepository.findAll();
        List<CarGenerationEntity> carGenerations = carGenerationRepository.findAll();
        CreateCarResponseDTO createCarResponseDTO = new CreateCarResponseDTO();
        createCarResponseDTO.setCarBrands(carBrands);
        createCarResponseDTO.setCarModels(carModels);
        createCarResponseDTO.setCarGenerations(carGenerations);
        return createCarResponseDTO;
        /* Кратко: Этот метод получает все данные о поколениях машин, марках и моделях
        из базы данных,затем собирает эти данные в объект DTO и возвращает его. DTO (Data Transfer Object)
        используется для передачи данных между слоями приложения */
    }

    public void storeCar(StoreCarProfileRequestDTO storeCarProfileRequestDTO) {
        CarEntity carEntity = new CarEntity();
        //ToDO переделать Id
        Long UserId = 13L;
        if (UserId != null) {
            Optional<UserEntity> userOptional = userRepository.findById(UserId);
            userOptional.ifPresent(carEntity::setUser);
        }
        Long BrandId = storeCarProfileRequestDTO.getBrandId();
        if (BrandId != null) {
            Optional<CarBrandEntity> brandOptional = carBrandRepository.findById(BrandId);
            brandOptional.ifPresent(carEntity::setBrand);
        }
        Long ModelId = storeCarProfileRequestDTO.getModelId();
        if (ModelId != null) {
            Optional<CarModelEntity> carModelOptional = carModelRepository.findById(ModelId);
            carModelOptional.ifPresent(carEntity::setModel);
        }
        Long GenerationId = storeCarProfileRequestDTO.getGenerationId();
        if (GenerationId != null) {
            Optional<CarGenerationEntity> carGenerationOptional = carGenerationRepository.findById(GenerationId);
            carGenerationOptional.ifPresent(carEntity::setGeneration);
        }
        carEntity.setName(storeCarProfileRequestDTO.getName());
        carEntity.setPlate(storeCarProfileRequestDTO.getPlate());
        carEntity.setVin(storeCarProfileRequestDTO.getVin());
        carRepository.save(carEntity);
    }

    public boolean deleteCarById(Long id) {
        Optional<CarEntity> carOptional = carRepository.findById(id);
        if (carOptional.isPresent()) {
            carRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public CarProfileResponseDTO carFindById(Long id) {
        CarEntity carEntity = carRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Машина с данным ID " + id + " не найдена"));
        CarProfileResponseDTO carProfileResponseDTO = new CarProfileResponseDTO();
        carProfileResponseDTO.setName(carEntity.getName());
        carProfileResponseDTO.setPlate(carEntity.getPlate());
        carProfileResponseDTO.setVin(carEntity.getVin());
        carProfileResponseDTO.setBrandId(carEntity.getBrand().getId());
        carProfileResponseDTO.setModelId(carEntity.getModel().getId());
        carProfileResponseDTO.setGenerationId(carEntity.getGeneration().getId());
        return carProfileResponseDTO;
    }

    public void updateCarForProfile(CarUpdateRequestDTO updateCarRequestDTO) {
        Optional<CarEntity> carOptional = carRepository.findById(updateCarRequestDTO.getId());
        if (carOptional.isPresent()) {
            CarEntity carEntity = carOptional.get();
            carEntity.setName(updateCarRequestDTO.getName());
            carEntity.setPlate(updateCarRequestDTO.getPlate());
            carEntity.setVin(updateCarRequestDTO.getVin());
            Long BrandId = updateCarRequestDTO.getBrandId();
            if (BrandId != null) {
                Optional<CarBrandEntity> brandOptional = carBrandRepository.findById(BrandId);
                brandOptional.ifPresent(carEntity::setBrand);
            }
            Long ModelId = updateCarRequestDTO.getModelId();
            if (ModelId != null) {
                Optional<CarModelEntity> carModelOptional = carModelRepository.findById(ModelId);
                carModelOptional.ifPresent(carEntity::setModel);
            }
            Long GenerationId = updateCarRequestDTO.getGenerationId();
            if (GenerationId != null) {
                Optional<CarGenerationEntity> carGenerationOptional = carGenerationRepository.findById(GenerationId);
                carGenerationOptional.ifPresent(carEntity::setGeneration);
            }
            carRepository.save(carEntity);
        }
    }


}


