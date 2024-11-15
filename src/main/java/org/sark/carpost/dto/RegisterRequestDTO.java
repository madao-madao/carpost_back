package org.sark.carpost.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterRequestDTO {
    @NotBlank(message = "Имя не может быть пустым или состоять только из пробелов")
    @Size(min = 3, max = 256, message = "Имя должно быть от 2 до 25 символов")
    private String login;

    @NotBlank(message = "Имя не может быть пустым или состоять только из пробелов")
    @Size(min = 2, max = 25, message = "Имя должно быть от 2 до 25 символов")
    private String name;

    @NotBlank(message = "Пароль не может быть пустым")
    @Size(min = 4, message = "Пароль должен быть не менее 4 символов")
    private String password;

    @Pattern(regexp = "^(\\+7|8)\\s?\\(?[0-9]{3}\\)?\\s?[0-9]{3}\\s?-?\\s?[0-9]{2}\\s?-?\\s?[0-9]{2}$",
            message = "Неверный формат телефонного номера")
    private String phone_number;
}