package org.sark.carpost.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class StoreCarProfileRequestDTO {
    @Size(min = 2, max = 25, message = "Имя машины должно быть от 2 до 25 символов")
    private String car_name;

    @Pattern(regexp = "^[A-Z][0-9]{3}[A-Z]{2}[0-9]{3}$",
            message = "Номер машины должен быть в формате A123BC123")
    private String car_number;

    private String vin;

    @Size(min = 1, max = 50, message = "Модель автомобиля должно содержать хотя бы одну букву")
    private String model;

    public String getCar_name() {
        return car_name;
    }
    public void setCar_name(String car_name) {
        this.car_name = car_name;
    }
    public String getCar_number() {
        return car_number;
    }
    public void setCar_number(String car_number) {
        this.car_number = car_number;
    }
    public String getVin() {
        return vin;
    }
    public void setVin(String vin) {
        this.vin = vin;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }


}
