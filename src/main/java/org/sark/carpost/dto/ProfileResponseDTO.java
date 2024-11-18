package org.sark.carpost.dto;

import lombok.Getter;
import lombok.Setter;
import org.sark.carpost.entity.CarEntity;
import java.util.ArrayList;

@Setter
@Getter
public class ProfileResponseDTO {

    private String name;

    private String phoneNumber;

    private int experience;

    private String drivingLicense;

    private ArrayList<CarEntity> cars;

}
