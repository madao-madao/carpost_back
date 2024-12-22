package org.sark.carpost.rest;

import jakarta.validation.Valid;
import org.sark.carpost.dto.ProfileResponseDTO;
import org.sark.carpost.dto.ProfileUpdateRequestDTO;
import org.sark.carpost.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class UserRestApiController {

    private final UserService userService;

    public UserRestApiController(UserService userService) {
        this.userService = userService;
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

    @GetMapping("/profile/edit")
    public ResponseEntity<?> editProfile() {
        return ResponseEntity.ok(userService.getUserProfileForEditDTO(userService.getUserFromToken()));
    }

    @PatchMapping("/profile/update")
    public ResponseEntity<?> updateProfile(@Valid @RequestBody ProfileUpdateRequestDTO profileUpdateRequestDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessages);
        }
        userService.updateProfile(profileUpdateRequestDTO, userService.getUserFromToken());
        return ResponseEntity.ok("Вы успешно редактировали профиль");
    }

    @GetMapping("/profile")
    public ResponseEntity<ProfileResponseDTO> getProfile() {
        ProfileResponseDTO profile = userService.getUserProfileDTO(userService.getUserFromToken());
        return ResponseEntity.ok(profile);
    }
}
