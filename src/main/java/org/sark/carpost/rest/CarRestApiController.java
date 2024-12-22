package org.sark.carpost.rest;

import jakarta.validation.Valid;
import org.sark.carpost.dto.CarProfileResponseDTO;
import org.sark.carpost.dto.CarUpdateRequestDTO;
import org.sark.carpost.dto.CreateCarResponseDTO;
import org.sark.carpost.dto.StoreCarProfileRequestDTO;
import org.sark.carpost.service.car.CarDataSeederService;
import org.sark.carpost.service.car.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CarRestApiController {

    private final CarDataSeederService carDataSeederService;

    private final CarService carService;

    public CarRestApiController(CarDataSeederService carDataSeederService, CarService carService) {
        this.carDataSeederService = carDataSeederService;
        this.carService = carService;
    }

    /**
     * Краткое описание:
     * Метод	Операция	                Описание
     * GET	    Получение данных	        Запрашивает данные с сервера без изменения.
     * POST	    Создание новых данных	    Отправляет данные на сервер для создания ресурса.
     * PUT	    Полное обновление данных	Заменяет весь ресурс на новый.
     * PATCH	Частичное обновление данных	Обновляет только часть ресурса.
     * DELETE	Удаление данных	            Удаляет ресурс с сервера.
     */

    @GetMapping("/car/seed/")
    public void seed() {
        carDataSeederService.seed();
    }

    @PostMapping("/profile/car/store")
    public ResponseEntity<?> storeCarProfile(@Valid @RequestBody StoreCarProfileRequestDTO storeCarProfileRequestDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessages);
        }
        carService.storeCar(storeCarProfileRequestDTO);
        return ResponseEntity.ok("Машина успешно добавлена");
    }

    @GetMapping("/profile/car/new")
    public ResponseEntity<CreateCarResponseDTO> newCar() {
        CreateCarResponseDTO createCarResponseDTO = carService.createCar();
        return ResponseEntity.ok(createCarResponseDTO);
    }

    @DeleteMapping("/profile/car/delete/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable("id") Long id) {
        boolean deleted = carService.deleteCarById(id);
        if (deleted) {
            return ResponseEntity.ok("Машина успешно удалена.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Машина не найдена.");
        }
    }

    @GetMapping("/profile/car/{id}")
    public ResponseEntity<CarProfileResponseDTO> getCar(@PathVariable("id") Long id) {
        CarProfileResponseDTO carProfileResponseDTO = carService.carFindById(id);
        return ResponseEntity.ok(carProfileResponseDTO);
    }

    @PatchMapping("/profile/car/update/{id}")
    public ResponseEntity<?> updateCar(@Valid @RequestBody CarUpdateRequestDTO carUpdateRequestDTO, BindingResult bindingResult ) {
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessages);
        }
        carService.updateCarForProfile(carUpdateRequestDTO);
        return ResponseEntity.ok("Информация по машине обновлена");
    }
}