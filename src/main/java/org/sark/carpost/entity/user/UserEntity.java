package org.sark.carpost.entity.user;

import jakarta.persistence.*;
import lombok.Data;
import org.sark.carpost.entity.car.CarEntity;

import java.util.Date;
import java.util.List;


@Data
@Entity
@Table(name = "\"user\"")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String name;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String password;
    @Column(name = "issue_date")
    private Date issueDate;
    @Column(name = "driving_license")
    private String drivingLicense;

    @OneToMany(mappedBy = "user")
    private List<CarEntity> cars;
}
