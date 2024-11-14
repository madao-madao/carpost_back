package org.sark.carpost.dto;

import lombok.Setter;
import java.util.Date;

@Setter
public class ProfileEditResponseDTO {

    private String name;

    private String number;

    private String driving_license;

    private Date issue_date;
}
