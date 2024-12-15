package org.sark.carpost.dto.CarApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarModelResponseDTO {
    String name;
}
