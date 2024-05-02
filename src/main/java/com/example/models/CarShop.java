package com.example.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CarShop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name of car shop is required")
    private String name;

    @NotNull(message = "Stuff is required or 0")
    @Min(value = 0, message = "Stuff can`t have negative amount")
    private Integer stuffAmount;

    @NotNull(message = "Shop must have status of work")
    private Boolean isWorking;

    public CarShop() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStuffAmount() {
        return stuffAmount;
    }

    public void setStuffAmount(Integer stuffAmount) {
        this.stuffAmount = stuffAmount;
    }

    public Boolean getWorking() {
        return isWorking;
    }

    public void setWorking(Boolean working) {
        isWorking = working;
    }
}
