package org.sark.carpost.dto.CarApi;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CarBrandResponseDTO {
    String name;
    List<CarModelResponseDTO> models;
}
