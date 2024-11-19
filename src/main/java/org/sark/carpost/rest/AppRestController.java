package org.sark.carpost.rest;

import jakarta.validation.Valid;
import org.sark.carpost.dto.*;
import org.sark.carpost.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AppRestController {
    private final UserService userService;

    @Autowired
    public AppRestController(UserService userService) {
        this.userService = userService;
    }
    /**
     * Краткое описание:
        Метод	Операция	                Описание
        GET	    Получение данных	        Запрашивает данные с сервера без изменения.
        POST	Создание новых данных	    Отправляет данные на сервер для создания ресурса.
        PUT	    Полное обновление данных	Заменяет весь ресурс на новый.
        PATCH	Частичное обновление данных	Обновляет только часть ресурса.
        DELETE	Удаление данных	            Удаляет ресурс с сервера.
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
    public ResponseEntity<?> storeCarProfile(@Valid @RequestBody StoreCarProfileRequestDTO storeCarProfileRequestDTO) {
        return ResponseEntity.ok("");
    }
    @GetMapping("/api/profile")
    public ResponseEntity<ProfileResponseDTO> getProfile() {
        ProfileResponseDTO profile = userService.getUserProfileDTO(13L);
        return ResponseEntity.ok(profile);
    }
    @GetMapping("api/profile/car/new")
    public ResponseEntity<?> newCar(){
        return ResponseEntity.ok("");
    }
}
