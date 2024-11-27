package org.sark.carpost.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StoreCarProfileRequestDTO {
    @Size(min = 2, max = 25, message = "Имя машины должно быть от 2 до 25 символов")
    private String name;

    @Pattern(regexp = "^[A-Z][0-9]{3}[A-Z]{2}[0-9]{3}$",
            message = "Номер машины должен быть в формате A123BC123")
    private String plate;

    private String vin;

    private Long brandId;

    private Long modelId;

    private Long generationId;
}