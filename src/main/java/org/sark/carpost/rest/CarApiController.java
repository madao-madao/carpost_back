package org.sark.carpost.rest;

import org.sark.carpost.service.car.CarDataSeederService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class CarApiController {
    private final CarDataSeederService carDataSeederService;

    public CarApiController(CarDataSeederService carDataSeederService) {
        this.carDataSeederService = carDataSeederService;
    }

    @GetMapping("/seed")
    public void getCarss() {
        carDataSeederService.seed(); // Возвращаем данные напрямую для проверки
    }
}