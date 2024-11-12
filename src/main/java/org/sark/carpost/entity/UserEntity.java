package org.sark.carpost.entity;

public class UserEntity {
    private String name;
    private String number;
    private String password;

    public UserEntity(String name, String number, String password) {
        this.name = name;
        this.number = number;
        //Добавить потом
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
}
