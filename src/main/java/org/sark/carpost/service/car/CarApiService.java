package org.sark.carpost.service.car;

import org.sark.carpost.dto.CarApi.CarBrandResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.Arrays;
import java.util.List;

@Service
public class CarApiService {

    private final RestTemplate restTemplate;
    private final String API_URL = "https://cars-base.ru/api/cars?full=1";

    public CarApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<CarBrandResponseDTO> fetchCarData() {
        try {
            CarBrandResponseDTO[] response = restTemplate.getForObject(API_URL, CarBrandResponseDTO[].class);
            return Arrays.asList(response);
        } catch (Exception e) {
            System.err.println("Ошибка при обращении к API: " + e.getMessage());
        }
        return List.of();
    }
}
