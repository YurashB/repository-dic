package org.example.dtos;

import java.time.LocalDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CarRequestDto {

    @NotBlank(message = "Car brand is required")
    public String brand;

    @NotBlank(message = "Car model is required")
    public String model;

    @NotNull(message = "Car horse power is required and must be numeric")
    public Integer horsePower;

    @NotNull(message = "Car date of introduction is required")
    public LocalDate introductionDate;

}
