package org.sark.carpost.dto;

import jakarta.annotation.Nullable;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;


@Setter
@Getter
public class ProfileUpdateRequestDTO {

//    private Long id;

    @Size(min = 2, max = 25, message = "Имя должно быть от 2 до 25 символов")
    private String name;

    @Nullable
    @Pattern(regexp = "^$|(\\+7|8)\\s?\\(?[0-9]{3}\\)?\\s?[0-9]{3}\\s?-?\\s?[0-9]{2}\\s?-?\\s?[0-9]{2}$",
            message = "Неверный формат телефонного номера")
    private String phoneNumber;

    @Nullable
    @Pattern(regexp = "^$|\\d{10}$", message = "Номер водительского удостоверения состоит из 4 цифр серии и 6 цифр номера")
    private String drivingLicense;

    @PastOrPresent(message = "Значение не может превышать текущей даты")
    @Temporal(TemporalType.DATE)
    private Date issueDate;

}