package org.sark.carpost.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
public class ProfileEditResponseDTO {

    private String name;

    private String number;

    private String drivingLicense;

    private Date issueDate;
}
