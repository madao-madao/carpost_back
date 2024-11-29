package org.sark.carpost.dto;

import lombok.Data;

@Data
public class CarProfileResponseDTO {

    private Long id;

    private String name;

    private String plate;

    private String vin;

    private Long brandId;

    private Long modelId;

    private Long generationId;

}
