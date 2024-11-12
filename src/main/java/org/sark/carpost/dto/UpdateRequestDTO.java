package org.sark.carpost.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UpdateRequestDTO {

    @Size(min = 2, max = 25, message = "Имя должно быть от 2 до 25 символов")
    private String name;

    @Pattern(regexp = "^(\\+7|8)\\s?\\(?[0-9]{3}\\)?\\s?[0-9]{3}\\s?-?\\s?[0-9]{2}\\s?-?\\s?[0-9]{2}$",
            message = "Неверный формат телефонного номера")
    private String number;

    @Size(min = 10, max = 10, message = "Номер водительского удостоверения состоит из 4 цифр серии и 6 цифр номера")
    private String driving_license;

    // TODO: разобраться с валидацией даты
    //@Max(value = 1735670400000L, message = "Дата должна быть не позднее 1 июня 2025 года")
    private int issue_date;



}
