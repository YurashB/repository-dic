package com.example.dtos;

public class CarShopResponseDto {

    public Integer id;

    public String name;

    public Integer stuffAmount;

    public Boolean isWorking;

    public CarShopResponseDto(Integer id, String name, Integer stuffAmount, Boolean isWorking) {
        this.id = id;
        this.name = name;
        this.stuffAmount = stuffAmount;
        this.isWorking = isWorking;
    }
}