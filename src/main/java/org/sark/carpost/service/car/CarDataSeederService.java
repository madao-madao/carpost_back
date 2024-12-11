package org.sark.carpost.service.car;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.sark.carpost.entity.car.CarBrandEntity;
import org.sark.carpost.entity.car.CarModelEntity;
import org.sark.carpost.repository.car.CarBrandRepository;
import org.sark.carpost.repository.car.CarModelRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class CarDataSeederService {

    private final CarBrandRepository carBrandRepository;
    private final CarModelRepository carModelRepository;

    public CarDataSeederService(CarBrandRepository carBrandRepository, CarModelRepository carModelRepository) {
        this.carBrandRepository = carBrandRepository;
        this.carModelRepository = carModelRepository;
    }

    public void getDataFromCarBase() {
        WebClient webClient = WebClient.builder()
                .baseUrl("https://cars-base.ru/api/cars?full=1")
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(10 * 1024 * 1024))
                .build();

        try {
            String carData = webClient.get()
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            ObjectMapper objectMapper = new ObjectMapper();
            List<Map<String, Object>> cars = objectMapper.readValue(carData, new TypeReference<List<Map<String, Object>>>() {});
            Logger logger = Logger.getLogger(CarDataSeederService.class.getName());
            Map<String, CarBrandEntity> brandsMap = cars.stream()
                    .map(car -> (String) car.get("brand"))
                    .filter(Objects::nonNull)
                    .filter(brandName -> !brandName.trim().isEmpty())
                    .distinct()
                    .map(brandName -> carBrandRepository.findByName(brandName)
                            .orElseGet(() -> {
                                CarBrandEntity brand = new CarBrandEntity();
                                brand.setName(brandName);
                                System.out.println("Сохраняем бренд: " + brandName);
                                return carBrandRepository.save(brand);
                            }))
                    .collect(Collectors.toMap(CarBrandEntity::getName, brand -> brand));

            List<CarModelEntity> models = cars.stream()
                    .map(car -> {
                        String brandName = (String) car.get("brand");
                        String modelName = (String) car.get("model");

                        // Проверяем, что бренд и модель не пустые
                        if (brandName == null || brandName.trim().isEmpty() || modelName == null || modelName.trim().isEmpty()) {
                            System.err.println("Пропущена запись с некорректными данными: " + car);
                            return null;
                        }

                        // Получаем бренд из карты
                        CarBrandEntity brand = brandsMap.get(brandName);

                        // Проверяем, что бренд существует
                        if (brand == null) {
                            System.err.println("Не найден бренд для модели: " + modelName);
                            return null;
                        }

                        CarModelEntity model = new CarModelEntity();
                        model.setName(modelName); // Устанавливаем имя модели
                        model.setCarBrand(brand); // Связываем с брендом

                        // Если поле "name" или "car_brand" пустое, пропускаем модель
                        if (model.getName() == null || model.getName().trim().isEmpty() || model.getCarBrand() == null) {
                            System.err.println("Ошибка: модель с пустым полем 'name' или 'brand': " + car);
                            return null;
                        }

                        return model;
                    })
                    .filter(Objects::nonNull) // Убираем записи с null
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Ошибка при получении или обработке данных: " + e.getMessage());
        }
    }
}

