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
     * Метод	Операция	                Описание
     * GET	    Получение данных	        Запрашивает данные с сервера без изменения.
     * POST	    Создание новых данных	    Отправляет данные на сервер для создания ресурса.
     * PUT	    Полное обновление данных	Заменяет весь ресурс на новый.
     * PATCH	Частичное обновление данных	Обновляет только часть ресурса.
     * DELETE	Удаление данных	            Удаляет ресурс с сервера.
     */
    @PostMapping("/api/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessages);
        }
        userService.addUser(registerRequestDTO);
        return ResponseEntity.ok("Пользователь успешно зарегистрирован");
    }
}
