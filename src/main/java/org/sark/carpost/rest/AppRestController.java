package org.sark.carpost.rest;

import jakarta.validation.Valid;
import org.sark.carpost.dto.RegisterRequestDTO;
import org.sark.carpost.dto.StoreCarProfileRequestDTO;
import org.sark.carpost.dto.ProfileUpdateRequestDTO;
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
        return ResponseEntity.ok(userService.getUserProfileDTObyId(13L));
    }
    @PostMapping("/api/profile/update")
    public ResponseEntity<?> updateProfile(@Valid @RequestBody ProfileUpdateRequestDTO profileUpdateRequestDTO) {
//        if(profileUpdateRequestDTO.getId() == null) profileUpdateRequestDTO.setId(13L);
        userService.updateProfile(profileUpdateRequestDTO, 13L);
        return ResponseEntity.ok("Вы успешно редактировали профиль");
    }
    @PostMapping("/api/profile/car/store")
    public ResponseEntity<?> storeCarProfile(@Valid @RequestBody StoreCarProfileRequestDTO storeCarProfileRequestDTO) {
        return ResponseEntity.ok("");
    }
}
