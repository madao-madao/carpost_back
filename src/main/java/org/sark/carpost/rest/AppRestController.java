package org.sark.carpost.rest;

import jakarta.validation.Valid;
import org.sark.carpost.dto.CarProfileResponseDTO;
import org.sark.carpost.dto.CarUpdateRequestDTO;
import org.sark.carpost.dto.CreateCarResponseDTO;
import org.sark.carpost.dto.StoreCarProfileRequestDTO;
import org.sark.carpost.dto.ProfileResponseDTO;
import org.sark.carpost.dto.ProfileUpdateRequestDTO;
import org.sark.carpost.dto.RegisterRequestDTO;
import org.sark.carpost.service.car.CarDataSeederService;
import org.sark.carpost.service.car.CarService;
import org.sark.carpost.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AppRestController {
    private final UserService userService;
    private final CarService carService;
    private final CarDataSeederService carDataSeederService;

    @Autowired
    public AppRestController(UserService userService, CarService carService, CarDataSeederService carDataSeederService) {
        this.userService = userService;
        this.carService = carService;
        this.carDataSeederService = carDataSeederService;
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
    @PostMapping("/api/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO, BindingResult bindingResult) {
        // Если есть ошибки валидации
        if (bindingResult.hasErrors()) {
            // Формируем список ошибок
            List<String> errorMessages = bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList());
            // Возвращаем ошибку с кодом 400 и списком ошибок
            return ResponseEntity.badRequest().body(errorMessages);
        }
        userService.addUser(registerRequestDTO);
        return ResponseEntity.ok("Пользователь успешно зарегистрирован");
    }

    @GetMapping("/api/profile/edit")
    public ResponseEntity<?> editProfile() {
        return ResponseEntity.ok(userService.getUserProfileForEditDTO(13L));
    }

    @PatchMapping("/api/profile/update")
    public ResponseEntity<?> updateProfile(@Valid @RequestBody ProfileUpdateRequestDTO profileUpdateRequestDTO, BindingResult bindingResult) {
//        if(profileUpdateRequestDTO.getId() == null) profileUpdateRequestDTO.setId(13L);
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessages);
        }
        userService.updateProfile(profileUpdateRequestDTO, 13L);
        return ResponseEntity.ok("Вы успешно редактировали профиль");
    }

    @PostMapping("/api/profile/car/store")
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

    @GetMapping("/api/profile")
    public ResponseEntity<ProfileResponseDTO> getProfile() {
        ProfileResponseDTO profile = userService.getUserProfileDTO(13L);
        return ResponseEntity.ok(profile);
    }

    @GetMapping("api/profile/car/new")
    public ResponseEntity<CreateCarResponseDTO> newCar() {
        CreateCarResponseDTO createCarResponseDTO = carService.createCar();
        return ResponseEntity.ok(createCarResponseDTO);
    }

    @DeleteMapping("/api/profile/car/delete/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable("id") Long id) {
        boolean deleted = carService.deleteCarById(id);
        if (deleted) {
            return ResponseEntity.ok("Машина успешно удалена.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Машина не найдена.");
        }
    }

    @GetMapping("api/profile/car/{id}")
    public ResponseEntity<CarProfileResponseDTO> getCar(@PathVariable("id") Long id) {
        CarProfileResponseDTO carProfileResponseDTO = carService.carFindById(id);
        return ResponseEntity.ok(carProfileResponseDTO);
    }

    @PatchMapping("/api/profile/car/update/{id}")
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

    @GetMapping("/test")
    public ResponseEntity<?> test(){
        carDataSeederService.getDataFromCarBase();
        return ResponseEntity.ok("");
    }
}
