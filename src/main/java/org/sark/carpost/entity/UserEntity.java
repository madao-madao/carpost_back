package org.sark.carpost.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Getter
@Data
@Entity
@Table(name = "\"user\"")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String login;
    private String name;
    private String phone_number;
    private String password;
    private Date issue_date;
    private String driving_license;

    public UserEntity() {
    }

}
