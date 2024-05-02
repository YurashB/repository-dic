package com.example.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CarShopRequestDto {

    @NotBlank(message = "Name of car shop is required")
    public String name;

    @NotNull(message = "Stuff is required or 0")
    @Min(value = 0, message = "Stuff can`t have negative amount")
    public Integer stuffAmount;

    @NotNull(message = "Shop must have status of work")
    public Boolean isWorking;

    public CarShopRequestDto(String name, Integer stuffAmount, Boolean isWorking) {
        this.name = name;
        this.stuffAmount = stuffAmount;
        this.isWorking = isWorking;
    }
}
